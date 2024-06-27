<!-- eslint-disable vue/valid-v-slot -->
<template>
   <v-data-table
   :headers="header"
   :items="filteredItems"
   item-key="product_name">

   <template v-slot:item.actions="{ item }">
      <v-icon
         class="me-2"
         size="small"
         @click="editItem(item)"
      >
         mdi-pencil
      </v-icon>
      <v-icon
         size="small"
         @click="deleteItem(item)"
      >
         mdi-delete
      </v-icon>
      </template>
      <template v-slot:no-data>
      <v-btn
         color="primary"
         @click="initialize"
      >
         Reset
      </v-btn>
      </template>

      <template v-slot:item.product_img_url="{item}" >
         <v-img :src="item.product_img_url"></v-img> 
      </template>

      <template v-slot:top>
   
      <v-card-title class="d-flex align-center pe-2">
         <v-icon icon="mdi-shopping-outline"></v-icon> &nbsp;
         Productos
         <v-spacer></v-spacer>

         <v-text-field
         v-model="search"
         density="compact"
         label="Search"
         prepend-inner-icon="mdi-magnify"
         variant="solo-filled"
         flat
         hide-details
         single-line
         ></v-text-field>

         <v-dialog max-width="500px">
            <template v-slot:activator="{ props }">
               <v-btn
               class="ma-2"
               color="primary"
               dark
               v-bind="props">
                  Agregar producto
               </v-btn>
            </template>
            <!--Agregar producto-->
            <v-card>
            <v-card-title>
               <span class="text-h5">Agregar un producto</span>
            </v-card-title>
            <v-card-text>
               <v-container>
                  <v-row>
                     <v-col
                     cols="12"
                     >
                     <v-text-field
                        v-model="editedItem.title"
                        label="Nombre del artículo"
                     ></v-text-field>
                     </v-col>
                     <v-col
                     cols="12"

                     >
                     <v-textarea
                        v-model="editedItem.description"
                        label="Descripción"
                     ></v-textarea>
                     </v-col>
                     <v-col
                     cols="6"
                     >
                     <v-text-field
                        v-model="editedItem.price"
                        label="Precio"
                     ></v-text-field>
                     </v-col>
                     <v-col
                     cols="6"
                     >
                     <v-text-field
                        v-model="editedItem.category"
                        label="Categoria"
                     ></v-text-field>
                     </v-col>
                     <v-col
                     cols="12"
                     >
                     <v-file-input
                        v-model="editedItem.image"
                        label="Imagen del producto"
                     ></v-file-input>
                     </v-col>
                  </v-row>
            
               </v-container>
               </v-card-text>

               <v-card-actions>
               <v-spacer></v-spacer>
               <v-btn
                  color="blue-darken-1"
                  variant="text"
                  @click="close"
               >
                  Cancel
               </v-btn>
               <v-btn
                  color="blue-darken-1"
                  variant="text"
                  @click="save"
               >
                  Save
               </v-btn>
               </v-card-actions>
            </v-card>


         </v-dialog>

         <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
               <v-card-title class="text-h5"
                  >Are you sure you want to delete this item?</v-card-title
               >
               <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue-darken-1" variant="text" @click="closeDelete"
                     >Cancel</v-btn
                  >
                  <v-btn
                     color="blue-darken-1"
                     variant="text"
                     @click="deleteItemConfirm"
                     >OK</v-btn
                  >
                  <v-spacer></v-spacer>
               </v-card-actions>
            </v-card>
         </v-dialog>
         
      </v-card-title>

      <v-divider></v-divider>    
      </template>


   </v-data-table>
</template>


<script lang="js" setup>
import { useProductosStore } from '@/stores/productosStore'
import { computed, onMounted,ref,watch } from 'vue'

onMounted(
   () => {
      store.fetchProductos();
   }
)

const dialog = ref(false);
const dialogDelete = ref(false);
const editedIndex = ref(-1);

const search = ref('');
const store = useProductosStore();
const items = computed(() => {
   return store.getProductos
})

const filteredItems = computed(() => {
   // Filtrar los items por título que coincida con el término de búsqueda, sin distinción entre mayúsculas y minúsculas
   return items.value.filter(item => 
      item.product_name.toLowerCase().includes(search.value.toLowerCase())
   );
});


const header = [
   {title: 'id', value: 'id_product', align: 'start'},
   {title: 'Nombre', value: 'product_name', align: 'start'},
   {title: 'Descripción', value: 'product_description', align: 'start'},
   {title: 'Precio', value: 'product_price', align: 'start'},
   {title: 'Categoria', value: 'categoria_producto', align: 'start'},
   {title: 'Imagen', value: 'product_img_url', align: 'center'},
   {title: 'Stock', value: 'product_stock', align: 'start'},
   {title: 'Acciones', value: 'actions', align: 'start'},

]
const defaultItem = {
   id: 0,
   title: '',
   description: '',
   price: 0,
   category: '',
   image: null,
};

const editedItem = ref({
   id: 0,
   title: '',
   description: '',
   price: 0,
   category: '',
   image: null,
});

const close = () => {
   dialog.value = false;
   editedItem.value = { ...defaultItem };
   editedIndex.value = -1;
};

const editItem = (item) => {
   editedIndex.value = items.value.indexOf(item);
   editedItem.value = { ...item };
   dialog.value = true;
};


watch(dialog, (val) => {
   if (!val) {
      close();
   }
});

watch(dialog, (val) => {
   if (!val) {
      close();
   }
});



</script>