$(function() {

	var shopId = getQueryString('shopId');

	var isEdit = shopId ? true : false;

	var shopInfoUrl = '/o2o_war_exploded/shop/getshopbyid?shopId=' + shopId;

	var initUrl = '/o2o_war_exploded/shop/getshopinitinfo';
	var editShopUrl = '/o2o_war_exploded/shop/registershop';

	if (isEdit) {
		editShopUrl = '/o2o_war_exploded/shop/modifyshop';
	}
	if (isEdit) {
		getInfo(shopId);
	} else {
		getCategory();
	}

	function getInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop = data.shop;
				$('#shop-name').val(shop.name);
				$('#shop-addr').val(shop.address);
				$('#shop-phone').val(shop.phoneNumber);
				$('#shop-desc').val(shop.desc);
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.id + '" selected>'
						+ shop.shopCategory.name + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.id + '">'
							+ item.name + '</option>';
				});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disabled','disabled');
				$('#area').html(tempAreaHtml);
				$("#area option[data-id='"+shop.area.id+"']").attr("selected","selected");

			}
		});
	}

	function getCategory() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.id
							+ '">' + item.name + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.id + '">'
							+ item.name + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#shop-category').removeAttr('disabled');
				$('#area').html(tempAreaHtml);
			}
		});
	}



	$('#submit').click(function() {
		var shop = {};
		if (isEdit) {
			shop.id = shopId;
		}
		shop.name = $('#shop-name').val();
		shop.address = $('#shop-addr').val();
		shop.phoneNumber = $('#shop-phone').val();
		shop.desc = $('#shop-desc').val();

		shop.shopCategory = {
			id : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			id : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};

		var shopImg = $("#shop-img")[0].files[0];
		var formData = new FormData();
		formData.append('shopImg', shopImg);
		formData.append('shopStr', JSON.stringify(shop));
		var verifyCodeActual = $('#j_captcha').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append("verifyCodeActual", verifyCodeActual);
		$.ajax({
			url : editShopUrl,
			type : 'POST',
			// contentType: "application/x-www-form-urlencoded; charset=utf-8",
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					if (isEdit){
						$('#captcha_img').click();
					} else{
						window.location.href="/shop/shoplist";
					}
				} else {
					$.toast('提交失败！');
					$('#captcha_img').click();
				}
			}
		});
	});

});
