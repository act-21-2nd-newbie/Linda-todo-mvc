<template>
  <div id="app">
    <TodoList :todo-list="todoList" :value="value"
              @checked="onChecked"
              @checked-all="onCheckedAll"
              @submit-input="onSubmitInput"
              @delete-input="onDeleteInput"
              @delete-all="onDeleteAll"/>
  </div>
</template>

<script>
import TodoList from "@/components/TodoList";
import { getData, postData, patchStatus, deleteData } from "@/service/todo-service";

export default {
  name: 'App',
  props:{

  },
  data(){
    return{
      todoList: [

      ],
      value:'',
    }
  },
  async mounted(){
    this.todoList = await getData();
    console.log(this.todoList);

  },
  components: {
    TodoList
  },
  computed:{
    newId(){
      return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
      });
    }
  },
  methods:{
    async onSubmitInput(index, value) {
      const response = await postData({details:value});
      console.log(response.data);
      this.todoList.push({id:response.data, details:value, status:true});
      console.log(this.todoList);
      document.getElementsByTagName("TodoList").value = '';
    },
    async onChecked(id, status){
      const response = await patchStatus(id, status);
      console.log(response);
      const item = this.todoList.findIndex((array) => array.id === id);
      this.todoList[item].status = status;
    },
    async onCheckedAll(status) {
     await Promise.all(this.todoList.map(function(todo){
       return patchStatus(todo.id, status);
      }));
      this.todoList = await getData();

    },
    async onDeleteInput(id){
      await deleteData(id);
      const item = this.todoList.findIndex((array) => array.id === id);
      console.log(item);
      this.todoList.splice(item, 1);
    },
    onDeleteAll(){

      this.todoList.forEach(async function(todo){
        if(todo.status === false)
          await deleteData(todo.id);
      })
      this.todoList = this.todoList.filter(item => item.status === true);
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
