/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 8/21/12
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
(function($){//��ֹ$������ͻ
    $(document).ready(function(){//dom���������ִ�к�������ֹjs����
        $(".j-button").button();
        $( "#tabs" ).tabs();
        $("#savePage").click(function(){
            var param = $('#webpage_form').serializeJSON();
            $.post('../saveTemplate/'+$("#componentCode").val()+".json",param,function(data) {
                alert(data.success);
            });
        });


        jQuery("#webpage_list").jqGrid({
            datatype: "json",
            colModel:[
                {name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true}},
                {name:'code',index:'code', label:"����", width:105,editable:true},
                {name:'name',index:'name', label:"����", width:105,editable:true},
                {name:'theme',index:'theme', label:"���", width:105,editable:true,
                    edittype:'select',
                    editoptions:{
                        value:{
                            "J_Page":"Ĭ��"
                        }
                    }
                },
                {name:'owner',index:'owner', label:"������", width:75,editable:true},
                {name:'uri',index:'uri', label:"URI", width:155,editable:true},
                {name:'description',index:'description', label:"��ע", width:105,editable:true},
                {name:'uri',index:'uri', label:"����", width:105,
                    formatter:function(rowId, cellval , colpos, rwdat, act){
                        var url = "/"+$("#webApp").val()+"/"+rowId;
                        var veiw = "<a href='"+url+"' target='_blank'>�鿴</a>";
                        var edit = "<a href='"+url+"?t_e_s"+"' target='_blank'>�༭</a>";
                        return veiw+"&nbsp;&nbsp;"+edit;
                    }
                }
            ],
            viewrecords: true,
            jsonReader: {
                repeatitems : false,
                id: "0"
            },
            editurl:"save.json",
            url : "list.json",
            pager: '#webpage_pagered'
        }).jqGrid('navGrid','#webpage_pagered',{edit:true,add:true,del:true});

    });
})(jQuery);