<template>
  <div class="users">
    <v-alert
        type="success"
        v-show="isSuccess"
    >{{ successMsg }}
    </v-alert>
    <user-table style="margin-bottom: 50px"></user-table>
  </div>
</template>

<script>
import UserTable from "@/components/UserTable";

export default {
  name: "Users",

  components: {
    UserTable,
  },
  data() {
    return {
      successMsg: '',
      isSuccess: false,
    }
  },
  methods: {
    init() {
      console.log(this.$cookies.get("success"))
      if (this.$cookies.get("success")) {
        this.isSuccess = true
        this.successMsg = 'User successfully added'
      }
    }
  },
  mounted() {
    this.init()
  },
  created() {
    setTimeout(() =>
        this.isSuccess = false, 3000)
  },
}
</script>
