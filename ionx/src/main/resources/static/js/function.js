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
						selectNivelAcesso.onchange = function() { updatePosition(responseUsers.data[i].id) };
						selectNivelAcesso.classList.add("form-select");
						selectNivelAcesso.id = responseUsers.data[i].id;

						let defaultOption = document.createElement("option");
						defaultOption.textContent = "Status";
						defaultOption.disabled = true;
						selectNivelAcesso.appendChild(defaultOption);
						for (let x = 0; x < responsePositions.data.length; x++) {
							let option = document.createElement("option");
							option.value = responsePositions.data[x].id;
							option.textContent = responsePositions.data[x].nome;
							if (responseUsers.data[i].position.id === responsePositions.data[x].id) {
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
				})
		})
		.catch(function(error) {
		})
}
function updatePosition(id) {
	let choice = document.getElementById(id).selectedIndex;

	axios({
		method: 'post',
		url: '/users/updatePosition',
		params: {
			userID: id,
			selectedPosition: choice
		}
	})
		.then(function(response) {
			let status = response.status;
			if (status === 200) {
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Alteração salva!',
					showConfirmButton: false,
					timer: 950
				})
			}
		})
}

function sendEmail(id) {

	Swal.fire({
		title: 'Enviar e-mail?',
		text: "Você deseja notificar os integrantes desta Prospect via e-mail?",
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Sim, eu desejo!',
		cancelButtonText: 'Não, não quero!'
	}).then((result) => {
		if (result.isConfirmed) {
			axios({
				method: 'post',
				url: '/email/sendEmail',
				params: {
					prospectId: id

				}
			})
				.then(function(response) {
					let status = response.status;
					if (status === 200) {
						Swal.fire({
							position: 'center',
							icon: 'success',
							title: 'E-mail enviado!',
							showConfirmButton: false,
							timer: 950
						})
					}
					else {
						Swal.fire({
							position: 'center',
							icon: 'error',
							title: 'Ops! O e-mail não foi enviado...',
							showConfirmButton: false,
							timer: 950
						})
					}
				})
		}
	})
}
