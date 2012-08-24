/*!
 * jQuery UI Accordion 1.8.23
 *
 * Copyright 2012, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Accordion
 *
 * Depends:
 *	jquery.ui.core.js
 *	jquery.ui.widget.js
 */
(function( $, undefined ) {

    $.widget( "ui.jweb", {
        options: {
        },

        _create: function() {
            var self = this,
                options = self.options;
            this._container();
            this._component();
        },
        _container :function(){
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
        },
        _component : function(){
            $( ".jweb-component" ).draggable({
                start: function(event, ui) {
                    $(this).css("z-index",10000000000000);
                }
            });
            $( ".jweb-resizable" ).resizable();
        }
    });

})(jQuery);