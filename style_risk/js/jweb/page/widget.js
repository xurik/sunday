/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 8/20/12
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
(function($){//防止$变量冲突
    $(document).ready(function(){//dom加载完后，再执行函数，防止js报错
        $("#tabs").tabs({
            select: function(event, ui) {
                var componentCode = ui.panel.id.split("-")[1];
                $("#componentCode").val(componentCode);
            }
        });
        $("#submitData").click(function(){
            $("body").mask("Waiting...");
            var param = $("#widgetForm").serializeJSON();
            var url = $("#widgetForm").attr("action");
            $.ajax({
                url: url,
                data : param,
                dataType : "json",
                type:'post',
                success: function(){
                    $("body").unmask("Waiting...");
                }
            });
        })
    });
})(jQuery);
