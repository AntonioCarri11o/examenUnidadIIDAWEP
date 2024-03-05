<template>
  <div class="principal d-flex mx-auto my-2">
    <b-modal ref="confirm" id="confirm" :title="'Eliminar libro'" hide-footer>
      <div class="w-100">
        <h4>Seguro que desea eliminar el libro?</h4>
        <div class="modal-buttons w-100 mt-4 d-flex justify-content-end">
          <b-button class="mx-1" @click="$bvModal.hide('confirm')">Cancelar</b-button>
          <b-button class="mx-1" variant="danger" @click="deleteBook()">Eliminar</b-button>
        </div>
      </div>
    </b-modal>
    <b-modal id="create" :title="'Crear libro'" hide-footer>
      <div class="w-100">
        <b-form class="w-100"
        @submit.prevent="checkFormC"
        action="https://vuejs.org/"
        method="post"
        >
            <b-form-group
            label="Nombre"
            label-for="name"
            >
              <b-form-input
              id="name"
              type="text"
              v-model="book.name"
              ></b-form-input>
            </b-form-group>
            <b-form-group
            label="Fecha de publicación"
            label-for="date"
            >
              <b-form-input
              id="date"
              type="date"
              v-model="book.publishDate"
              ></b-form-input>
            </b-form-group>
            <b-form-group
            label="Autor"
            label-for="autor"
            >
              <b-form-input
              id="autor"
              type="text"
              v-model="book.autor"
              ></b-form-input>
            </b-form-group>
            <b-form-group
            label="Imágen (URL)"
            label-for="image"
            >
              <b-form-input
              id="image"
              type="text"
              v-model="book.imageURL"
              ></b-form-input>
            </b-form-group>
            <div class="modal-buttons d-flex w-100 justify-content-end mt-4">
          <b-button class="mx-1" type="button" @click="$bvModal.hide('create')">Cancelar</b-button>
          <b-button class="mx-1" type="submit"variant="primary">Guardar</b-button>
        </div>
          </b-form>
      </div>
    </b-modal>
    <b-modal ref="update" id="update" :title="'Actualizar libro'" hide-footer>
      <div class="w-100">
        <b-form class="w-100"
        @submit.prevent="checkFormU"
        action="https://vuejs.org/"
        method="post"
        >
            <b-form-group
            label="Nombre"
            label-for="name"
            >
              <b-form-input
              id="name"
              type="text"
              v-model="bookU.name"
              ></b-form-input>
            </b-form-group>
            <b-form-group
            label="Fecha de publicación"
            label-for="date"
            >
              <b-form-input
              id="date"
              type="date"
              v-model="bookU.publishDate"
              ></b-form-input>
            </b-form-group>
            <b-form-group
            label="Autor"
            label-for="autor"
            >
              <b-form-input
              id="autor"
              type="text"
              v-model="bookU.autor"
              ></b-form-input>
            </b-form-group>
            <b-form-group
            label="Imágen (URL)"
            label-for="image"
            >
              <b-form-input
              id="image"
              type="text"
              v-model="bookU.imageURL"
              ></b-form-input>
            </b-form-group>
            <div class="modal-buttons d-flex w-100 justify-content-end mt-4">
          <b-button class="mx-1" type="button" @click="$bvModal.hide('create')">Cancelar</b-button>
          <b-button class="mx-1" type="submit"variant="primary">Guardar</b-button>
        </div>
          </b-form>
      </div>
    </b-modal>
    <div class="side-principal">
      <div class="zone-container">
        <b-button class="m-auto" variant="primary"  @click="$bvModal.show('create')">+</b-button>
      </div>
      <div class="zone-container"
      style="background-image: url('https://cdn0.iconfinder.com/data/icons/app-user-interface-line-7/48/Primary-Challenge04_App_User_Interface-Line__Solid-48px-03-512.png');"
      @drop="onDrop($event, 'delete')"
      @dragover.prevent
      @dragenter.prevent
      ></div>
      <div class="zone-container" style="background-image: url('https://cdn.iconscout.com/icon/free/png-256/free-edit-1912229-1617698.png');"
      @drop="onDrop($event, 'update')"
      @dragover.prevent
      @dragenter.prevent
      ></div> 
    </div>
    <div class="content-principal">
      <div class="card-container row mx-auto my-2"
      @dragover.prevent
      @dragenter.prevent
      >
        <div v-for="book in books" class="col-lg-4 col-md-6 col-sm-12 mb-4"
        :key="book.id"
        draggable
        @dragstart="startDrag($event, book)"
        >
          <div class="book-card d-flex flex-column"
          >
          <div class="w-100">
            <img :src="book.imageURL">
          </div>
          <div class="mt-auto">
            <h5>Nombre: {{ book.name }}</h5>
            <h5>Author: {{ book.autor }}</h5>
            <h5>Fecha: {{ book.publishDate }}</h5>
          </div>
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
      dBook: null,
      book: {
        name: null,
        autor: null,
        publishDate: null,
        imageURL: null
      },
      bookU: {
        name: null,
        autor: null,
        publishDate: null,
        imageURL: null
      }
    }
  },
  mounted() {
    this.list();
  },
  components: {
  },
  methods: {
    checkFormC() {
      try{
        bookService.save(this.book);
      } catch(err) {
        console.error(err);
      }
    },
    checkFormU() {
      try{
        bookService.updateBook(this.bookU);
      } catch(err) {
        console.error(err);
      }
    },
    startDrag(evt, book) {
      evt.dataTransfer.dropEffect = "move";
      evt.dataTransfer.effectAllowed = "move";
      evt.dataTransfer.setData("bookId", book.id);
    },
    onDrop(evt, action) {
      const bookId = evt.dataTransfer.getData("bookId");
      const book = this.books.find((book) => book.id == bookId);
      if (action == 'delete') {
        this.dBook = book.id;
        this.$refs.confirm.show();
      }
      if (action == 'update') {
        this.getById(book.id);
      }
    },
    deleteBook() {
      try {
        bookService.deleteBook(this.dBook);
        window.location.reload();
      } catch(err) {
        console.error(err);
      }
    },
    cancelD() {
      this.dBook = null;
    },
    async list() {
      try {
        const response = await bookService.list();
        this.books = response.data;
      } catch(err) {
        throw(err);
      }
    },
    async getById(id) {
        //this.bookU = bookService.getById(book.id);
        const rep = await bookService.getById(id);
        this.bookU = rep.data;
        this.$refs.update.show();
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
}
.principal {
  width: 70%;
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
    border: 1px dotted #A4A4A4;
    background-repeat: no-repeat;
  }
  .content-principal {
    width: 80%;
    .card-container {
      width: 90%;
    }
  }
  .book-card {
    -webkit-user-select: none;
    cursor: pointer;
    padding: 1em;
    box-sizing: border-box;
    box-shadow: 3px 4px 19px 0px rgba(0,0,0,0.75);
    -webkit-box-shadow: 3px 4px 19px 0px rgba(0,0,0,0.75);
    -moz-box-shadow: 3px 4px 19px 0px rgba(0,0,0,0.75);
    height: 100%;
    border-radius: 1em;
    img {
      width: 100%;
    }
  }
  .modal-content {

  }
  .modal-buttons {

  }
}

</style>
