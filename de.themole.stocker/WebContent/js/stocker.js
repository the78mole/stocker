var stocker = {
	init : function() {
		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			var activatedTab = e.target // active tab
			var previousTab = e.relatedTarget // previous tab
			Cookies.set('de.themole.stocker.items.tab.selected', activatedTab.hash);
			if (activatedTab.hash == "#listItems") {
				$.get({
					url : "./items",
					success : function(data) {
						var mydata = data;
						var tbody = $("#itemListTable tbody");
						tbody.empty();
						$.each(data, function(index, element) {
							insertListItemRow(tbody, element);
						});
					}
				});
			}
		});
		$(".navbar-nav a").on('click', function(e) {
			e.preventDefault(); // stops link form loading
			var navref = $(this).attr('href');
			showHideTabs('.div-init-hide', navref)
			Cookies.set('de.themole.stocker.navigation.selected', navref);
			if (navref == "#items") {
				switchToLastItemTab();
			}
		});
		$(".navbar-header a").on('click', function(e) {
			e.preventDefault(); // stops link form loading
			var navref = $(this).attr('href');
			showHideTabs('.div-init-hide', navref)
			Cookies.set('de.themole.stocker.navigation.selected', navref);
		});
		// Initially show the home div
		// var dihshow = $("#home");
		// dihshow.show(); //get the href and use it find which div to show
		var selnav = Cookies.get('de.themole.stocker.navigation.selected');
		if (selnav === undefined) {
			selnav = "#home";
		}
		showHideTabs('.div-init-hide', selnav)
		//$(selnav).show();
		switchToLastItemTab();
		
		$("#addItemSubmit").on('click', submitAddItemForm);
	}
};

function insertListItemRow(table, data) {	
	var tabrow = '';
	tabrow += '<tr>\n';
	tabrow += '	<td>' + data.id + '</td>\n';
	tabrow += '	<td>' + <NYI> + '</td>\n';
	tabrow += '	<td>' + data.name + '</td>\n';
	tabrow += '	<td>' + data.created + '</td>\n';
	tabrow += '	<td>' + data.description + '</td>\n';
	tabrow += '	<td>\n';
	tabrow += '	  <a href="#" id="actionItem_0_delete" role="button" class="btn btn-danger">\n';
	tabrow += '	    <span class="glyphicon glyphicon-trash"></span>\n';
	tabrow += '	  </a>\n';
	tabrow += '	  <a href="#" id="actionItem_0_edit" role="button" class="btn btn-primary">\n';
	tabrow += '	    <span class="glyphicon glyphicon-edit"></span>\n';
	tabrow += '	  </a>\n';
	tabrow += '	  <a href="#" id="actionItem_0_in" role="button" class="btn btn-success">\n';
	tabrow += '	    <span class="glyphicon glyphicon-plus"></span>\n';
	tabrow += '	  </a>\n';
	tabrow += '	  <a href="#" id="actionItem_0_out" role="button" class="btn btn-warning">\n';
	tabrow += '	    <span class="glyphicon glyphicon-minus"></span>\n';
	tabrow += '	  </a>\n';
	tabrow += '	</td>\n';
	tabrow += '</tr>\n';
	table.append(tabrow);
}

function submitAddItemForm() {
	var addform = $("#formAddItem");
	var formdata = addform.serializeJSON();
	$.ajax({
		url      : "./item",
		method   : "put",
		data     : formdata,
		dataType : "json",
		success  : function(data) {
			alert("success adding");
		},
		error		 : function (data) {
			alert("error happened adding");
		}
	});
}

function switchToLastItemTab() {
	var selitemtab = Cookies.get('de.themole.stocker.items.tab.selected');
	if (selitemtab === undefined) {
		selitemtab = "#searchItems";
	}
	var tabref = $('a[href="' + selitemtab + '"]');
	if (tabref !== undefined) {
		tabref.tab('show');
	}
}

function showHideTabs(hideclass, showid) {
	var dih = $(hideclass);
	dih.hide(); // hides all content divs
	var tabref = showid;
	var dihshow = $(tabref);
	var doctest = $.contains(document, dihshow[0]);	
	dihshow.show(); // get the href and use it find which div to show
}
