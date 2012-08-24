package com.alibaba.china.jweb.core.service.impl;

import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.exception.VelocityExcepiton;
import com.alibaba.china.jweb.core.tree.Node;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Properties;

/**
 * com.alibaba.china.jweb.core.service.impl:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 11:09 PM
 */
public class VelocityTemplateServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(VelocityTemplateServiceImpl.class);
    @Autowired
    @Qualifier("repoPath")
    private String repoPath;
    @Autowired
    @Qualifier("vmCommonPath")
    private String vmCommonPath;
    @Autowired
    @Qualifier("encoding")
    private String encoding;


    private void buildHtml(Node<Widget> node, VelocityContext velocityContext) {
        postorder(node);
    }

    private void postorder(Node<Widget> p) {  //∫Û–Ú≈≈¡–
        if (p != null) {
            postorder(p.getLeft());
            postorder(p.getRight());
            visit(p);
        }
    }

    private static void visit(Node<Widget> p) {
        System.out.print(p.getData().getId() + " ");
    }

}
