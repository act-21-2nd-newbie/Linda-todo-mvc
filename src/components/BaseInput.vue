<template>
  <div class="todoInput">
    <input type="checkbox" v-show="todoList.length !== 0" @change="$emit('checked-all')"/>
    <input class="input" placeholder="What needs to be done?" @input="$emit('input', $event.target.value)" @keydown.enter="$emit('submit-input', $event.target.value)" :value="value"/>
  </div>
</template>

<script>
export default {
  name: "BaseInput",
  props: {
    todoList: Array,
    value: {
      type: String,
      default: '',
    }
  },
  methods:{

  }
}
</script>

<style scoped>
.todoInput{
  display:flex;
  justify-content: flex-start;
  align-items: center;
  width: 500px;
  height: 100px;
  background-color: inherit;
  border-style: solid;
  border-color: gray transparent gray transparent;
}



.input{

  background-color: inherit;
  border-style:none;
  font-size: 40px;
  font-style: italic;
}
</style>