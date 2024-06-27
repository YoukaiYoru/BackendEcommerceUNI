<template>
  <v-app>
    <NavBar v-if="!$route.path.startsWith('/admin')"/>
    <NavBarAdmin v-if="$route.path.startsWith('/admin')" />

    <v-navigation-drawer
      v-model="drawer"
      temporary
      width="300"
    >
    <v-list dense>
      <v-list-item
        v-for="(item, index) in items"
        :key="index"
        :to="item.props.to"
        link
      >
        <v-list-item-icon>
          <v-icon>{{ item.props.prependIcon }}</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
    </v-navigation-drawer>

    <v-main>
      <router-view />
    </v-main>
    <AppFooter />
  </v-app>
</template>

<script setup>
  import { ref,provide } from 'vue';  
  import NavBar from '@/components/NavBar.vue';
  import AppFooter from '@/components/AppFooter.vue';
  const drawer = ref(false)
  const toggleDrawer = () => {
    drawer.value = !drawer.value;
  };
  provide('drawer', drawer);
  provide('toggleDrawer', toggleDrawer);
const items = ref([
  {title: 'Libros', 
    props: {prependIcon: "mdi-book-open-page-variant", to: "/productos"},
  },
  {title: 'Accesorios', 
    props: {prependIcon: "mdi-necklace", to: "/productos"},
  },
  {title: 'Tecnología', 
    props: {prependIcon: "mdi-laptop", to: "/productos"}
  },
  {title: 'Papelería', 
    props: {prependIcon: "mdi-note-edit-outline", to: "/productos"},
  },
  {title: 'Accesorios de computación', 
    props: {prependIcon: "mdi-mouse", to: "/productos"},
  },
  {title: 'Bolsas y mochilas', 
    props: {prependIcon: "mdi-bag-personal-outline", to: "/productos"},
  },
  {title: 'Ropa', 
    props: {prependIcon: "mdi-tshirt-crew-outline", to: "/productos"},
  },

])
</script>


<style>

/* Importa la fuente desde Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

/* Aplica la fuente globalmente */
html, body {
  font-family: 'Poppins', sans-serif;
}
</style>