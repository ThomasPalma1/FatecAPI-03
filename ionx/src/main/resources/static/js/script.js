function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);


}

function drop(ev) {
    ev.preventDefault();

var data = ev.dataTransfer.getData("text");
    ev.currentTarget.appendChild(document.getElementById(data));

let dados = ev.currentTarget.id
    console.log("ITEM: " + data + " - COLUNA: " + ev.currentTarget.id);
console.log(dados)

let id = document.getElementById("idCliente").innerHTML
    console.log(id)



    //let statusID = document.getElementById('cardStatus').value
    fetch("http://localhost:8080/edit/" + id,{
     method:"PUT",
     headers: {"Content-Type": "application/json"},
    body: JSON.stringify({
        status: dados,
        })

    }).then((resposta) => resposta.json())
    .then((data) => console.log(data))



}

