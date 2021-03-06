$(function () {
    var listUrl = '/o2o_war_exploded/shop/getproductcategorylist';
    var addUrl = '/o2o_war_exploded/shop/addproductcategorys';
    var deleteUrl = '/o2o_war_exploded/shop/removeproductcategory';
    getList();

    function getList() {
        $
            .getJSON(
                listUrl,
                function (data) {
                    if (data.success) {
                        var dataList = data.data;
                        $('.category-wrap').html('');
                        var tempHtml = '';
                        dataList
                            .map(function (item, index) {
                                tempHtml += ''
                                    + '<div class="row row-product-category now">'
                                    + '<div class="col-33 product-category-name">'
                                    + item.name
                                    + '</div>'
                                    + '<div class="col-33">'
                                    + item.priority
                                    + '</div>'
                                    + '<div class="col-33"><a href="#" class="button delete" data-id="'
                                    + item.id
                                    + '">删除</a></div>'
                                    + '</div>';
                            });
                        $('.category-wrap').append(tempHtml);
                    }
                });
    }

    $('#new')
        .click(
            function () {
                var tempHtml = '<div class="row row-product-category temp">'
                    + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
                    + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
                    + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
                    + '</div>';
                $('.category-wrap').append(tempHtml);
            });
    $('#submit').click(function () {
        var tempArr = $('.temp');
        var productCategoryList = [];
        tempArr.map(function (index, item) {
            var tempObj = {};
            tempObj.name = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if (tempObj.name && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: addUrl,
            type: 'POST',
            data: JSON.stringify(productCategoryList),
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                    getList();
                } else {
                    $.toast('提交失败！');
                }
            }
        });
    });

    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();

        });
    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            var target = e.currentTarget;
            $.confirm('确定么?', function () {
                $.ajax({
                    url: deleteUrl,
                    type: 'POST',
                    data: {
						productCategoryId: target.dataset.id
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $.toast('删除成功！');
                            getList();
                        } else {
                            $.toast('删除失败！');
                        }
                    }
                });
            });
        });
});