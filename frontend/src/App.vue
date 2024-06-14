<template>
  <v-app>

    <v-nav-bar >
      <v-nav-item to="/">Home</v-nav-item>
      <v-nav-item to="/about">About</v-nav-item>
      <v-nav-item to="/contact">Contact</v-nav-item>
    </v-nav-bar>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>


<style scoped>

v-nav-bar {

  background-color: aqua;
}

</style>

<script setup>
  //
</script>
