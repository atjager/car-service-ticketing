<template>
  <div class="customers">
    <v-alert
        type="success"
        v-show="isSuccess"
    >{{ successMsg }}
    </v-alert>
    <customer-table style="margin-bottom: 50px"></customer-table>
  </div>
</template>


<script>
import CustomerTable from "@/components/CustomerTable";

export default {
  name: "Customers",

  components: {
    CustomerTable,
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
        this.successMsg = 'Customer successfully added.'
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

