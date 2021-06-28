function salvarUser() {
	var id = $("#id").val();
	var nome = $("#nome").val();
	var idade = $("#idade").val();
	
	if(nome != null && nome.trim() != '' 
	   && idade != null && idade.trim() != '')
	{
		$.ajax({
			method: "POST",
			url: "salvar",
			data: JSON.stringify({ id: id, nome: nome, idade: idade }),
			contentType: "application/json; charset=utf-8",
			success: function(response) {
	
				alert("Salvo com sucesso, id cadastro: " + response.id);
				document.getElementById('formCadUser').reset();
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao salvar: " + xhr.responseText);
		})
	}
	else{
		alert("Necessário preencher todas as informações!")
	}
}


function pesquisarUser() {
	var nome = $("#nameBusca").val();
	
	$.ajax({
		method: "GET",
		url: "buscarnome",
		data: "nome=" + nome,
		contentType: "application/json; charset=utf-8",
		success: function(response) {
			/*Limpa tabela*/
			$("#resultDados > tbody > tr").remove();

			/*Retorna informações*/
			for (var i = 0; i < response.length; i++) {
				$("#resultDados > tbody").append('<tr id=' + response[i].id + '>'
													+ '<td>' + response[i].id + '</td>'
													+ '<td>' + response[i].nome + '</td>'
													+ '<td>' + response[i].idade + '</td>'
													+ '<td> <button class="btn btn-dark" type="button" onClick="selectEdit(' + response[i].id + ');">Selecionar</button></td>'
													+ '<td><button class="btn btn-danger" type="button" onClick="deleteUser(' + response[i].id + ');">Delete</button></td>'
												+'</tr>');
			}
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Erro ao buscar: " + xhr.responseText);
	})
}



function selectEdit(id) {

	$.ajax({
		method: "GET",
		url: "buscaruserid",
		data: "iduser=" + id,
		contentType: "application/json; charset=utf-8",
		success: function(response) {

			var id = $("#id").val(response.id);
			var nome = $("#nome").val(response.nome);
			var idade = $("#idade").val(response.idade);

			$("#pesquisa").modal('hide');
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Erro ao buscar usuario por ID " + xhr.responseText);
	})
}



function deleteUser(id) {

	if (confirm("Deseja realmente deletar?")) {
		$.ajax({
			method: "DELETE",
			url: "delete",
			data: "iduser=" + id,
			success: function(response) {

				$('#' + id).remove();		
				alert(response);

			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao excluir o cadastro! " + xhr.responseText);
		})
	}
}



function deletarDaTela() {
	var id = $("#id").val();

	if (id != null && id.trim() != '') {
		deleteUser(id);

		document.getElementById('formCadUser').reset();
	}
	else {
		alert("Nenhum cadastro selecionado!")
	}
}