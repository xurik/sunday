/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 8/21/12
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
(function($){//��ֹ$������ͻ
    $(document).ready(function(){//dom���������ִ�к�������ֹjs����
        $("#main").jweb();
        /*
        $(".jweb-container").droppable(
            {
                drop:function(event, ui){
                    if($(ui.draggable).parent().attr("id") != $(this).attr("id")){
                        $(ui.draggable).css("top",0)
                        $(ui.draggable).css("left",0)
                    }
                    $(ui.draggable).draggable({ appendTo: $(this) });
                    $(ui.draggable).appendTo( this );
            }
            }
        );
        $( ".jweb-component" ).draggable({
            start: function(event, ui) {
                $(this).css("z-index",10000000000000);
            }
        });
        $( ".jweb-resizable" ).resizable();
        */
    });
})(jQuery);