<template>
  <div class="todoList">
    <h1 class="title">todos</h1>
    <div class="todoListBox">
      <BaseInput :todoList="todoList" :value="value" @submit-input="onSubmitInput" @checked-all="onCheckedAll"/>
      <div v-show="currentTab==0">
        <div class="todos">
          <div v-for="todo in todoList" :key="todo.id">
            <Todo :id="todo.id" :details="todo.details" :status="todo.status" @checked="onChecked" @delete-input="onDeleteInput"/>
          </div>
        </div>
      </div>
      <div v-show="currentTab==1">
        <div class="todos">
          <div v-for="todo in todoFilter(true)" :key="todo.id">
            <Todo :id="todo.id" :details="todo.details" :status="todo.status" @checked="onChecked" @delete-input="onDeleteInput"/>
          </div>
        </div>
      </div>
      <div v-show="currentTab==2">
        <div class="todos">
          <div v-for="todo in todoFilter(false)" :key="todo.id">
            <Todo :id="todo.id" :details="todo.details" :status="todo.status" @checked="onChecked" @delete-input="onDeleteInput"/>
          </div>
        </div>
      </div>
      <div class="tabBox">
        <button class="tabs" v-for="(tab, index) in tabs" :key="tab"
                v-bind:class="{active: currentTab === index}"
                @click="currentTab = index">
          {{tab}}
        </button>
        <button class="tabs" v-show="showDeleteCompleted !== 0"
                @click="$emit('delete-all')">
          Clear Completed
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Todo from "./Todo";
import BaseInput from "@/components/BaseInput";

export default {
  name: "TodoList",
  components:{
    BaseInput,
    Todo,
  },
  props:{
    todoList: Array,
    value:{
      type: String,
      default: '',
    }
  },
  data(){
    return{
      id:"a",
      currentTab: 0,
      tabs: ['All', 'Active', 'Completed'],
      status: false,
    }
  },
  computed:{
    showDeleteCompleted(){
      return this.todoFilter(false).length;
    }
  },
  methods:{

    onSubmitInput(value) {
      this.$emit('submit-input', this.id, value);
      this.id = this.id + 'a';
    },
    onChecked(id, status){
      this.$emit('checked', id, status);
    },
    onCheckedAll(){
      this.$emit('checked-all', this.status);
      this.status = !this.status;
    },
    onDeleteInput(id) {
      this.$emit('delete-input', id);
    },
    todoFilter(status){
      return this.todoList.filter(st => st.status === status);
    }

  }
}
</script>

<style scoped>
.title{
  font-family: "Arial";
  color : black;
  font-size: 100px;

}
.todos{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.todoList{
  width: 500px;
  border-style: none;
  background: lightblue;
  opacity: 0.7;
  padding: 20px;
}

.tabs{
  border-style: none;
  background-color: transparent;
  font-size: 30px;
  margin-top: 10px;
}
button:hover {
  border-style: solid;
  border-radius: 10px/10px;
  border-width: thin;
  border-color: whitesmoke;
}
button:focus{
  border-style: solid;
  border-radius: 10px/10px;
  border-width: thin;
  border-color: white;
}
</style>