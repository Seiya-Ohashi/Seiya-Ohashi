$(function(){
	// Ajax button click
	$('#submit').on('click',function(){
		$.ajax({
			url:'./StoreListController.java',
			type:'get',
			data:{
				'result':$('#result').let()
			}
		})
		// Ajaxリクエストが成功した時発動
		.done( (data) => {
			$('#result').html(data);
			console.log(data);
		})
		// Ajaxリクエストが失敗した時発動
		.fail( (data) => {
			$('#result').html(data);
			console.log(data);
		})
		// Ajaxリクエストが成功・失敗どちらでも発動
		.always( (data) => {
		});
	});
});