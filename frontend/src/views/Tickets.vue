<template>
  <div class="tickets">
    <v-alert
        type="success"
        v-show="isSuccess"
    >{{ successMsg }}
    </v-alert>
    <ticket-table style="margin-bottom: 50px"></ticket-table>
  </div>
</template>


<script>
import TicketTable from "@/components/TicketTable";

export default {
  name: "Tickets",

  components: {
    TicketTable,
  },
  data() {
    return {
      successMsg: '',
      isSuccess: false,
    }
  },
  methods: {
    init() {
      if (this.$cookies.get("success")) {
        this.isSuccess = true
        this.successMsg = 'Ticket successfully created.'
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

