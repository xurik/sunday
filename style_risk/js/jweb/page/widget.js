/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 8/20/12
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
(function($){//��ֹ$������ͻ
    $(document).ready(function(){//dom���������ִ�к�������ֹjs����
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
