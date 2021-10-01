<template>
  <div class="todo" @mouseenter="isMouseOver = true" @mouseleave="isMouseOver = false">
    <input type="checkbox" id="id" :checked="!status"
           @change="$emit('checked', id, !status)"/>
    {{details}}
    <button class="deleteTodo" v-if="isMouseOver==true"
            @click="onDeleteInput" style="float:right">x</button>
  </div>
</template>

<script>
export default {
  name: "Todo",
  props:{
    id: String,
    details: String,
    status: Boolean,
  },
  data() {
    return{
      isMouseOver: false
    }
  },
  methods:{
    onDeleteInput(){
      console.log(this.id);
      this.$emit('delete-input', this.id);
    }
  }
}
</script>

<style scoped>
.todo{
  display: flex;
  width: 500px;
  height: 100px;
  border-style: solid;
  border-bottom-color: white;
  border-top-color: transparent;
  border-left: transparent;
  border-right: transparent;
  border-width: thin;
  justify-content: flex-start;
  align-items: center;
  font-size: 30px;
  color: black;
}

.deleteTodo{
  margin-left:auto;
  border-style: none;
  background-color: transparent;
  font-size: 30px;
  font-family: "Arial Rounded MT Bold";
  color: slategray;
}


.todo input[type="checkbox"]{
  border-radius: 10px;
  size: A4;
}
</style>