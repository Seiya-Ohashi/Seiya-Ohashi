$(function(){
	let value = '';
	document.getElementById('submit').onclick = function (e) {
		// HTMLでの送信をキャンセル
		e.preventDefault();
		// 操作対象のフォーム要素を取得(検索欄に入力した店舗名)
		value = $('[name="keyword"]').val();
		$.ajax({
			url: '/storeList/search', // リクエストを送信する先のURLを指定
			type: 'get', // HTTP通信の種類を指定
			data: {	// フォームデータを指定
				keyword : value
			},
			datatype:'json' // サーバから返されるデータの型を指定
		  }).done(function (data) {
			// 通信成功時のコールバック処理
			$(function(){
				let element;
				let storeId;
				let obj = JSON.stringify(data);
				obj = JSON.parse(obj);
				// console.log(data.length);
				// console.log(Object.keys(obj));
				element = document.querySelector('nav.page');
				element = document.querySelector('style');
				// HTMLを初期化
				$('table.table tbody').html('');

				if(document.querySelector('nav.page') != null){
					element = document.querySelector('nav.page');
					element.remove();
				}else if(document.querySelector('style') != null) {
					element = document.querySelector('style');
					element.remove();
				}
				// HTMLを生成
				$(
					'<tr>'+
						'<th class="info col-xs-2">店舗名</th>'+
						'<th class="info col-xs-2" colspan="3">住所</th>'+
						'<th class="info col-xs-2">店舗詳細</th>'+
					'</tr>').appendTo('table.table tbody');
				//---------------------------------
				// 検索結果(店舗情報)を表示する

				// ※負荷になるそうなので、いつか書き方を変える
				// 最初の要素を変数に格納
				// let html = '<p class="add">追加要素1</p>';
				// for文で変数にさらに要素を追加していく
				// for (var i=2; i<=4; i++) {
				//  html += '<p class="add">追加要素'+i+'</p>';
				// }
				// 要素が詰まった変数をappendする
				// $('div').append(html);
				//---------------------------------
				Object.keys(obj).forEach(function(key) {
					storeId = obj[key].storeId;
					$(
					'<tr class = "item">'+
						'<td>' +
							obj[key].storeName +
						'</td>' +
							'<td colspan="3">'+
								obj[key].address1 +
								obj[key].address2 +
								obj[key].address3 +
								obj[key].address4 +
							'</td>'+
						'<td>' +
							'<a class = "btn btn-primary"'+
							'href = ' + '/storeDetail/' + storeId + '>'+ '詳細' +'</a>'+
						'</td>' + 
					'</tr>').appendTo('table.table tbody');
				});
				$('form.pagination-form').html('');
			});
			$.getScript('/js/jquery.pagination.js', function(){
				const displayItemCount = 6;
				$('.items').pagination({
					itemElement : 'tr.item', // アイテムの要素
					displayItemCount : 6,
					wrapElement : 'nav',
					paginationClassName : 'page',
					paginationInnerClassName : 'pagination justify-content-center',
					prevBtnText : '前へ',
					nextBtnText : '次へ',
					prevBtnClassName : 'prev-page page-item',
					nextBtnClassName : 'next-page page-item'
				});
				$('nav.page p.prev-page').prependTo('ul');
				$('nav.page p.next-page').appendTo('ul');
				if (data.length <= displayItemCount) {
					$('nav.page p.next-page').attr({
						class : 'next-page page-item disabled'
					});
				} else {
					$('nav.page p.next-page').attr({
						class : 'next-page page-item'
					});
				}
				$('nav.page a').attr({
					class : 'page-link'
				});
				$('nav.page button').attr({
					class : 'page-link'
				});
				
				// let currentButton = document.querySelector('li.current[aria-selected="true"] a.page-link');
				// currentButton.style.cssText = "z-index: 3;color: #fff;background-color: #007bff;border-color: #007bff;";
				// 新しいstyle要素を作成
				// let newStyle = document.createElement('style');
				// newStyle.type = 'text/css';
				// CSSの内容を書く
				// newStyle.innerText = '.page-link:focus {' +
				// 	'z-index: 3;' +
				// 	'outline: 0;' +
				// 	'box-shadow: none;' +
				// '}'
				// HEAD要素の最後に作成したstyle要素を追加
				// document.getElementsByTagName('head').item(0).appendChild(newStyle);

			});
		  }).fail(function (data) {
			// 通信失敗時のコールバック処理
			document.getElementById('store').innerHTML = '失敗';
		  }).always(function (data) {
			// 常に実行する処理

		  });
	};
});