<template>
  <el-row :gutter="10">
    <el-col :span="24">
      <div class="panel">
        <div class="panel-heading">
          <slot name="header"></slot>
        </div>
        <div class="panel-body">
          <slot name="body"></slot>
        </div>
      </div>
    </el-col>
  </el-row>
</template>
