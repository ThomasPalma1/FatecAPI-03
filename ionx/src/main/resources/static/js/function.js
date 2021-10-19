function logar(event) {
	axios.post('/auth/verificarLogin', {}, {
		params: {
			email: document.getElementById("InputEmail1").value,
			senha: document.getElementById("InputPassword1").value
		}

	})
		.then(function (response) {
			let status = response.status;
			if (status === 200) {
				window.location.href = "/home";
			}
		})
		.catch(function (error) {
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
			.then(function (response) {
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
				.then(function (response) {
					let status = response.status;
					if (status === 200) {
						window.location.href = "/";
					}
				})
		}
	})


}
