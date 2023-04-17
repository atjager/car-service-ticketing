<template>
  <div class="users">
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
}
</script>
