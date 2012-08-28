package com.alibaba.china.jweb.core.service.impl;

import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.WebPage;
import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.exception.VelocityExcepiton;
import com.alibaba.china.jweb.core.exception.WidgetException;
import com.alibaba.china.jweb.core.repository.ComponentDao;
import com.alibaba.china.jweb.core.repository.WidgetDao;
import com.alibaba.china.jweb.core.service.ComponentService;
import com.alibaba.china.jweb.core.service.TemplateService;
import com.alibaba.china.jweb.core.tree.Node;
import com.alibaba.china.jweb.core.util.JacksonUtil;
import com.alibaba.china.jweb.core.util.PropertyConfigurer;
import com.alibaba.china.jweb.core.util.WriterUtil;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * com.alibaba.china.jweb.core.service.impl:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 9:13 PM
 */
@org.springframework.stereotype.Component
public class TemplateServiceImpl implements TemplateService {
    private static final RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
    @Autowired
    private ComponentService componentService;

    @Autowired
    private WidgetDao widgetDao;

    @Autowired
    private ComponentDao componentDao;
    private final static Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);
    @Autowired
    @Qualifier("repoPath")
    private String repoPath;
    @Autowired
    @Qualifier("vmCommonPath")
    private String vmCommonPath;
    @Autowired
    @Qualifier("encoding")
    private String encoding;


    public void initTemplate() throws ParseException, IOException {
        RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
        Iterator<Component> iterator = componentDao.findAll().iterator();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            String template = component.getTemplate();
            if(template == null){
                template = "";
            }
            StringReader reader = new StringReader(template);
            SimpleNode node = runtimeServices.parse(reader, component.getTemplate() + "/" + component.getCode());
        }
    }

    /**
     * Velocity 属性初始化
     *
     * @throws com.alibaba.china.jweb.core.exception.VelocityExcepiton
     *
     */
    @PostConstruct
    public void init() throws VelocityExcepiton {
        try {
            Properties prop = new Properties();
            prop.setProperty("file.resource.loader.path",
                    new File(new File(this.repoPath), this.vmCommonPath).getPath());
            // 打开cache
            prop.setProperty("file.resource.loader.cache", "true");
            prop.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
            prop.setProperty("runtime.log.logsystem.log4j.logger.level", "INFO");
            prop.setProperty("input.encoding", encoding);
            prop.setProperty("output.encoding", encoding);
            prop.setProperty("default.contentType", "text/html;charset=" + encoding);
            // 允许在循环中set null值
            prop.setProperty("directive.set.null.allowed", "true");
            Velocity.init(prop);
        } catch (Exception e) {
            String message = "Init Properties error!";
            logger.error(message, e);
            throw new VelocityExcepiton(message, e);
        }
    }

    public String renderHtml(Node<Widget> node, Map parameter,WebPage webPage) {
        Map<Long, List<String>> contextMap = new HashMap<Long, List<String>>();
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("j_parameter", parameter);
        postorder(node, parameter, contextMap);
        List<String> list = contextMap.get(webPage.getId());
        String str = "";
        if(list != null && !list.isEmpty()){
            str = (String)list.get(0);
        }
        return str;
    }

    private void postorder(Node<Widget> p, Map parameter, Map<Long, List<String>> contextMap) {  //后序排列
        if (p != null) {
            postorder(p.getLeft(), parameter, contextMap);
            postorder(p.getRight(), parameter, contextMap);
            visit(p, parameter, contextMap);
        }
    }

    private void visit(Node<Widget> p, Map parameter, Map<Long, List<String>> contextMap) {
        Widget data = p.getData();
        Writer writer = new StringWriter();
        try {
            Template template = getTemplate(data.getType(), data.getComponentCode());

            VelocityContext velocityContext = createVelocityContext(data, parameter);

            if(p.getLeft() != null){

                List<String> children = contextMap.get(p.getLeft().getData().getId());
                if(children != null && children.size() > 0){
                    List<String> tmp = new ArrayList<String>();
                    for(int i=children.size()-1;i>=0;i--){
                        tmp.add(children.get(i));
                    }
                    velocityContext.put("widgets",tmp);
                }
            }
            if(StringUtils.isNotBlank(data.getLoopChildren())){
                renderLoop(data.getLoopChildren(),parameter,velocityContext);
            }
            template.merge(velocityContext, writer);
            List<String> widgetList = new ArrayList<String>();
            if(p.getRight() != null){
                widgetList = contextMap.get(p.getRight().getData().getId());
            }
            widgetList.add(writer.toString());
            contextMap.put(data.getId(),widgetList);

        } catch (Exception e) {
            throw new VelocityException("page render error!id:" + data.getId(), e);
        } finally {
            WriterUtil.clossWriter(writer);
        }
        System.out.println(writer.toString());
    }

    private void renderLoop(String json,Map parameter, VelocityContext velocityContext){
        Map<String,List<Long>> loopMap = JacksonUtil.toObject(json, new TypeReference<Map<String, List<Long>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if(loopMap == null || loopMap.isEmpty()){
            return;
        }
        for(Map.Entry<String,List<Long>> entry : loopMap.entrySet()){
            String key = entry.getKey();
            List<Long> value = entry.getValue();
            if(StringUtils.isNotBlank(key) && value != null){
                for(Long id : value){
                    Widget data = widgetDao.findOne(id);
                    if(data == null){
                        logger.error("can not find widget!id="+id);
                        continue;
                    }

                    Component component = componentService.getByCode(data.getComponentCode());
                    Map<String,?> parameters = JacksonUtil.toObject(data.getParameters(),new TypeReference<Map<String, ?>>() {
                        @Override
                        public Type getType() {
                            return super.getType();
                        }
                    });
                    Writer writer = new StringWriter();
                    VelocityContext context = createVelocityContext(data,parameter);
                    try{
                        Template template = getTemplate(data.getType(), data.getComponentCode());
                        template.merge(velocityContext, writer);

                        List<String> loops = new ArrayList<String>();
                        if(velocityContext.get(key) != null){
                            loops = (List<String>)velocityContext.get(key);
                        }
                        loops.add(writer.toString());
                        velocityContext.put(key,loops);
                    }catch (Exception e){
                        throw new VelocityException("page render error!id:" + data.getId(), e);
                    }finally {
                        WriterUtil.clossWriter(writer);
                    }

                }
            }
        }
    }

    private VelocityContext createVelocityContext(Widget data, Map parameter) {
        Map<String, String> widgetParameter = JacksonUtil.toObject(data.getParameters(), new TypeReference<Map<String, String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        VelocityContext velocityContext = new VelocityContext(widgetParameter);
        velocityContext.put("J_PARAMETER",parameter);
        velocityContext.put("J_WEB_PAGE",parameter.get("J_WEB_PAGE"));
        velocityContext.put("J_WIDGET_ID",data.getId());
        return velocityContext;
    }

    /**
     * 获取模版
     *
     * @param type
     * @param code
     * @return
     */
    private Template getTemplate(String type, String code) throws ParseException {
        Assert.notNull(type);
        Assert.notNull(code);
        Component component = componentDao.findByCode(code);
        String vt = component.getTemplate();
        if(vt == null){
            vt = "";
        }
        StringReader reader = new StringReader(vt);
        SimpleNode node = runtimeServices.parse(reader, component.getTemplate() + "/" + component.getCode());
        Template template = new Template();
        template.setRuntimeServices(runtimeServices);
        template.setData(node);
        template.initDocument();
        return template;
    }

}
