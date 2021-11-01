function logar(event) {
	axios.post('/auth/verificarLogin', {}, {
		params: {
			email: document.getElementById("InputEmail1").value,
			senha: document.getElementById("InputPassword1").value
		}

	})
		.then(function(response) {
			let status = response.status;
			if (status === 200) {
				window.location.href = "/home";
			}
		})
		.catch(function(error) {
			Swal.fire({
				icon: 'error',
				title: 'Erro',
				text: 'Os dados inseridos não estão corretos!',
			})
		})

}

function cadastrar(event) {
	console.log("cadastrando");

	let nomeInput = document.getElementById("input-nome").value;
	let sobrenomeInput = document.getElementById("input-sobrenome").value;
	let emailInput = document.getElementById("input-email").value;
	let contatoInput = document.getElementById("input-contato").value;
	let senha1 = document.getElementById("input-Password1").value;
	let senha2 = document.getElementById("input-Password2").value;

	if (senha1 === senha2) {

		axios({
			method: 'post',
			url: '/users/create',
			data: {
				nome: nomeInput,
				sobrenome: sobrenomeInput,
				contato: contatoInput,
				email: emailInput,
				senha: senha1,
			}
		})
			.then(function(response) {
				let status = response.status;
				if (status === 200) {
					window.location.href = "/login";
				}
			})

	}
	else {
		Swal.fire({
			icon: 'error',
			title: 'Erro',
			text: 'As senhas digitadas não correspondem!',
		})
	}

}

function logout(event) {
	Swal.fire({
		title: 'Você tem certeza que quer sair?',
		text: "Se sim, será redirecionado para a página inicial.",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#44b53e',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Quero!',
		cancelButtonText: 'Não quero!'
	}).then((result) => {
		if (result.isConfirmed) {
			axios({
				method: 'get',
				url: '/auth/logout'
			})
				.then(function(response) {
					let status = response.status;
					if (status === 200) {
						window.location.href = "/";
					}
				})
		}
	})


}

function allUsers(event) {
	axios.get('/users/all', {}, {

	})
		.then(function(responseUsers) {

			axios({
				method: 'get',
				url: '/positions/all'
			})
				.then(function(responsePositions) {
					for (let i = 0; i < responseUsers.data.length; i++) {
						let tr = document.createElement("tr");
						let th = document.createElement("th");
						th.scope = "row";
						th.textContent = i + 1;

						let tdNome = document.createElement("td");
						tdNome.textContent = responseUsers.data[i].nome;

						let tdEmail = document.createElement("td");
						tdEmail.textContent = responseUsers.data[i].email;

						let tdNivelAcesso = document.createElement("td");

						let selectNivelAcesso = document.createElement("select");
						selectNivelAcesso.classList.add("form-select");

						let defaultOption = document.createElement("option");
						defaultOption.textContent = "Status...";
						selectNivelAcesso.appendChild(defaultOption);
						for (let x = 0; x < responsePositions.data.length; x++) {
							let option = document.createElement("option");
							option.value = responsePositions.data[x].id;
							option.textContent = responsePositions.data[x].nome;

							if (responseUsers.data[i].idPosition.id === responsePositions.data[x].id) {
								option.selected = true;
							}

							selectNivelAcesso.appendChild(option);
						}
						tdNivelAcesso.appendChild(selectNivelAcesso);

						tr.appendChild(th);
						tr.appendChild(tdNome);
						tr.appendChild(tdEmail);
						tr.appendChild(tdNivelAcesso);

						let tbody = document.getElementById("usersList");
						tbody.appendChild(tr);
					}







					// <tr>
					// 	<th scope="row">2</th>
					// 	<td>Floyd Miles</td>
					// 	<td>albert.cruz@example.com</td>
					// 	<td>
					// 		<select class="form-select" >
					// 			<option selected>Status...</option>
					// 			<option value="1">Administrador</option>
					// 			<option value="2">Vendedor</option>
					// 			<option value="3">Gerente</option>
					// 		</select>
					// 	</td>
					// </tr>
				})
		})
		.catch(function(error) {
		})
}
