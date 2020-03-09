<template>
  <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    :datatype="selection"
    style="width: 100%">
    <el-table-column
      type="index"
      label="编号"
      :index="indexMethod">
    </el-table-column>
    <el-table-column
      prop="toAddress"
      label="toAddress"
      width="180">
    </el-table-column>
    <el-table-column
      prop="balance"
      label="金额"
      width="180">
    </el-table-column>
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="备注">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handlePass(scope.$index, scope.row)">通过</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">拒绝</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {/* eslint-disable */
  data() {
    return {
      tableData: [{
        toAddress: '0004',
        sign: '0002',
        balance: 1000,
        applyId: 1,
        date: '2020-03-08',
        timestamp: new Date().getTime()
      }],
    }
  },
  methods: {
    indexMethod(index) {
      return index * 2;
    },
    handleDelete(index,row) {
      this.$message({
        message: '拒绝成功',
        type: 'success'
      })
    },handlePass(index,row){

      this.$http.post(
        'http://localhost:8080/api/aidCheck',
        this.tableData[0]).then(function (res) {
        console.log(res)
        console.log(res.data.data)

        this.$message({
          message: '通过成功'+row.name,
          type: 'success'
        })
      })

    }
  }
}
</script>
