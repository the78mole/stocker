var stocker = {
	init : function() {
		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			var activatedTab = e.target // active tab
			var previousTab = e.relatedTarget // previous tab
			Cookies.set('de.themole.stocker.items.tab.selected', activatedTab.hash);
			if (activatedTab.hash == "#listItems") {
				updateItemsList();
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

function updateItemsList() {
	$.get({
		url : "./items",
		success : function(data) {
			var mydata = data;
			var tbody = $("#itemListTable tbody");
			tbody.empty();
			$.each(data, function(index, element) {
				insertListItemRow(tbody, element);
			});
			$('a[name="itemsDelete"]').click(itemsDeleteClick);
			$('a[name="itemsEdit"]').click(itemsEditClick);
			$('a[name="itemsIn"]').click(itemsInClick);
			$('a[name="itemsOut"]').click(itemsOutClick);
		}
	});	
}

function itemsDeleteClick(event) {
	var tid = $(event.currentTarget).attr('id');
	var itemid = tid.replace(/^.*_(.*)$/, '$1');
	$.ajax({
		url      : "./item/" + itemid,
		method   : "delete",
		dataType : "json",
		success  : function(data) {
			$("#itemsListRow_" + itemid).remove();
		},
		error		 : function (data, error, expn) {
			alert(error + " happened deleting: " + expn);
		}		
	});
}

function itemsEditClick(event) {
	var tid = $(event.currentTarget).attr('id');
}

function itemsInClick(event) {
	var tid = $(event.currentTarget).attr('id');
}

function itemsOutClick(event) {
	var tid = $(event.currentTarget).attr('id');
}

function insertListItemRow(table, data) {	
	var tabrow = '';
	tabrow += '<tr id="itemsListRow_' + data.id + '">\n';
	tabrow += '	<td>' + data.id + '</td>\n';
	tabrow += '	<td>' + "<NYI>" + '</td>\n';
	tabrow += '	<td>' + data.name + '</td>\n';
	tabrow += '	<td>' + data.created + '</td>\n';
	tabrow += '	<td>' + data.description + '</td>\n';
	tabrow += '	<td>\n';
	tabrow += '	  <a name="itemsDelete" href="#" id="actionItemDelete_' + data.id + '" role="button" class="btn btn-danger">\n';
	tabrow += '	    <span class="glyphicon glyphicon-trash"></span>\n';
	tabrow += '	  </a>\n';
	tabrow += '	  <a name="itemsEdit" href="#" id="actionItemEdit_' + data.id + '" role="button" class="btn btn-primary">\n';
	tabrow += '	    <span class="glyphicon glyphicon-edit"></span>\n';
	tabrow += '	  </a>\n';
	tabrow += '	  <a name="itemsIn" href="#" id="actionItemIn_' + data.id + '" role="button" class="btn btn-success">\n';
	tabrow += '	    <span class="glyphicon glyphicon-plus"></span>\n';
	tabrow += '	  </a>\n';
	tabrow += '	  <a name="itemsOut" href="#" id="actionItemOut_' + data.id + '" role="button" class="btn btn-warning">\n';
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
			// alert("success adding");
		},
		error		 : function (data, error, expn) {
			alert(error + " happened adding: " + expn);
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
