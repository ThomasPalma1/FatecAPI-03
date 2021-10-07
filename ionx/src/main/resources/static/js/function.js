function logar(event) {
    axios.post('/auth/verificarLogin', {}, {
        params: {
            email: document.getElementById("InputEmail1").value,
            senha: document.getElementById("InputPassword1").value
        }

    })
        .then(function (response) {
            let status = response.status;
            if (status === 200){
               window.location.href = "/home";
            }
            console.log(response);
        })

}