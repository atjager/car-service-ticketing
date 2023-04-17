<template>
  <v-app>
    <v-app-bar
        app
        color="blue-grey lighten-2"
        dense
        elevation="3"

    >
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title style="font-family: 'Arial Black'; font-size: 40px">Car service ticketing</v-toolbar-title>

      <v-spacer></v-spacer>


    </v-app-bar>

    <sidebar :drawer="drawer" @close-drawer="drawer = false"></sidebar>

    <v-main style="border: 0px solid red; position: absolute; top:48px; bottom: 48px; left:20px; right: 20px;">
      <router-view/>
    </v-main>
    <v-footer padless fixed color="white">
      <v-col
          class="text-center"
          cols="12"
      >
        {{ new Date().getFullYear() }} — <strong>Car service ticketing</strong>
      </v-col>
    </v-footer>

  </v-app>
</template>

<script>
import sidebar from "@/components/Sidebar";


export default {
  name: 'App',
  components: {
    sidebar
  },
  data: () => ({
    drawer: false
  }),
};
</script>