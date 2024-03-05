<template>
  <div class="principal d-flex">
    <b-modal hide-footer></b-modal>
    <div class="side-principal">
      <div class="zone-container">
        <b-button class="m-auto" variant="primary">+</b-button>
      </div>
      <div class="zone-container"
      style="background-image: url('https://cdn0.iconfinder.com/data/icons/app-user-interface-line-7/48/Primary-Challenge04_App_User_Interface-Line__Solid-48px-03-512.png');"
      @drop="onDrop($event, 2)"
      @dragover.prevent
      @dragenter.prevent
      ></div>
      <div class="zone-container" style="background-image: url('https://cdn.iconscout.com/icon/free/png-256/free-edit-1912229-1617698.png');"></div>
    </div>
    <div class="content-principal">
      <div class="card-container row h-100 mx-auto my-2"
      @dragover.prevent
      @dragenter.prevent
      >
        <div v-for="book in books" class="col"
        :key="book.id"
        draggable
        @dragstart="startDrag($event, book)"
        >
          <div class="book-card"
          >
            <h5>Nombre: {{ book.name }}</h5>
            <h5>Author: {{ book.autor }}</h5>
            <h5>Fecha: {{ book.publishDate }}</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import bookService from './service/Book';
export default {
  name: 'App',
  data() {
    return {
      books: [],
    }
  },
  mounted() {
    this.list();
  },
  components: {
  },
  methods: {
    startDrag(evt, book) {
      evt.dataTransfer.dropEffect = "move";
      evt.dataTransfer.effectAllowed = "move";
      evt.dataTransfer.setData("itemID", book.id);
    },
    onDrop(evt, list) {
      const itemID = evt.dataTransfer.getData("itemID");
      const item = this.books.find((book) => book.id == itemID);
      item.list = list;
      alert('drop');
    },
    async list() {
      try {
        const response = await bookService.list();
        this.books = response.data;
      } catch(err) {
        throw(err);
      }
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
.principal {
  background-color: rebeccapurple;
  width: 70%;
  margin: 1em auto 0 auto;
  position: relative;
  .side-principal {
    position: absolute;
    width: 20%;
    height: 100%;
    right: 0;
  }
  .zone-container {
    width: 100%;
    height: 33.3%;
    display: flex;
    background-size: 90%;
    background-position: center;
    border: 1px solid #2c3e50;
    background-repeat: no-repeat;
  }
  .content-principal {
    width: 80%;
    height: 100%;
    .card-container {
      width: 90%;
    }
  }
  .book-card {
    width: 90%;
    -webkit-user-select: none;
    cursor: pointer;
  }
}

</style>
