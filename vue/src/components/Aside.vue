<template>
    <div style="width: 200px">
        <el-menu
                default-active="user"
                :default-openeds="[1]"
                class="el-menu-vertical-demo">
            <el-sub-menu index="1">
                <template #title>
                    <el-icon><location /></el-icon>
                    <span>系统管理</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item index="user">用户管理</el-menu-item>
                </el-menu-item-group>
            </el-sub-menu>
            <el-menu-item index="data">数据管理</el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "Aside"
    }
</script>

<style scoped>

</style>