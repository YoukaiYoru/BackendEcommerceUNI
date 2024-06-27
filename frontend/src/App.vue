<template>
  <v-app>
    <NavBar v-if="!$route.path.startsWith('/admin')"/>
    <NavBarAdmin v-if="$route.path.startsWith('/admin')" />
    <v-main>
      <router-view />
    </v-main>
    <AppFooter />
  </v-app>
</template>

<script setup>
  import NavBar from '@/components/NavBar.vue';
  import AppFooter from '@/components/AppFooter.vue';

</script>


<style>

/* Importa la fuente desde Google Fonts */
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

/* Aplica la fuente globalmente */
html, body {
  font-family: 'Poppins', sans-serif;
}
</style>