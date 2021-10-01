import axios from "axios";

async function getData() {
    const response = await axios.get("api/tasks");
    return response.data.map(item =>{
        return {"id": item.identifier, "details": item.details, "status": item.status==="Active"}
        }
    )

}

async function postData(newTodo) {
    const response = await axios.post("api/tasks", newTodo);
    return response;
}

async function patchStatus(id, status){
    const response = await axios.patch("api/tasks/"+id, {"status": status?"Active":"done"});
    return response;
}

async function deleteData(id) {
    const response = await axios.delete("api/tasks/"+id);
    return response;
}

export { getData, postData, patchStatus, deleteData }