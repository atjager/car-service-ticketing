<template>
  <div class="home">
    <v-container fluid>
      <v-row dense>
        <v-col
            v-for="card in cards"
            :key="card.title"
            :cols="card.flex"
        >
          <v-card
              @click="$router.push(card.url)"
              outlined
              elevation="2"
              title
          >
            <v-img
                :src="card.src"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200px"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>

              <!--              <v-btn icon>-->
              <!--                <v-icon>mdi-heart</v-icon>-->
              <!--              </v-btn>-->

              <!--              <v-btn icon>-->
              <!--                <v-icon>mdi-bookmark</v-icon>-->
              <!--              </v-btn>-->

              <!--              <v-btn icon>-->
              <!--                <v-icon>mdi-cog</v-icon>-->
              <!--              </v-btn>-->
              <div><strong>{{ card.text }}</strong></div>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
<!--    <img alt="Vue logo" src="../assets/logo.png" />-->
<!--    <HelloWorld msg="Welcome to Your Vue.js App" />-->
  </div>
</template>

<script>
import usersPic from '@/assets/users.png'
import customerPic from '@/assets/customers.jpg'
import ticketsPic from '@/assets/tickets.png'
import settingsPic from '@/assets/settings.png'

export default {
  data() {
    return {
      cards: [
        {title: 'Users', src: usersPic, flex: 6, url: '/users', text: 'Application users'},
        { title: 'Customers', src: customerPic, flex: 6, url: '/customers', text: 'List of our customers'},
        {title: 'Tickets', src: ticketsPic, flex: 6, url: '/tickets', text: 'List of open tickets'},
        {title: 'Settings', src: settingsPic, flex: 6, url: '/settings', text: 'Application preferences'},
      ],
    }
  },
  name: "Home",
  components: {

  },
};
</script>
