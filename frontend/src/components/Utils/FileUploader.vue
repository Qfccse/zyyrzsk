<template>
    <div class="file_uploader">
        <div style="float:left;">
            <el-upload
                :on-preview="handlePictureCardPreview"
                :disabled="upload.isUploading"
                :multiple="false"
                :on-change="handleFileChange"
                :before-remove="handleFileRemove"
                :auto-upload="false"
                :file-list="upload.fileList"
                :class="{ hide: uploadDisabled }"
                :list-type="listType"
                :accept="fileType?fileType:'image/*'"
                :limit="limit"
                action='https'
                :on-success="handleUploadSuccess"
                :on-remove="handleRemove"
                :show-file-list="fileLisToRight===false"
            >
                <el-button
                    :icon="icon"
                    :style="buttonStyle"
                    v-if="this.listType!=='picture-card'">
                    <span v-if="icon.length===0">选择文件</span></el-button>
                <i v-else class="el-icon-plus"></i>
            </el-upload>
            <el-button @click="fileUpload" v-if="show&&upload.fileList.length!==0">上传</el-button>
        </div>
        <div style="float: left;height: 50px;line-height: 50px"
             v-if="this.fileLisToRight===true&&this.upload.fileList.length!==0">
            <span style="width: 130px;
            display: inline-block;
            float: left;
            margin-left: 10px;
            margin-right: 10px;
            color: #1593f9;
            overflow: hidden">
                {{ this.upload.fileList[0].raw.name }}
            </span>
            <i class="el-icon-close" @click="fileRemove"></i>
        </div>
        <div style="float: left" v-if="this.fileLisToRight===true&&this.upload.fileList.length!==0">
            <el-button @click="fileUpload" style="border: none;margin-top: 5px">上传</el-button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'FileUploader',
    props: {
        limit: {
            type: Number,
            default: 9
        },
        fileType: {
            type:String,
            default:'image/*'
        },
        url:{
            type: String,
            default: ""
        },
        listType:{
            type: String,
            default:""
        },
        id:{
            // type:Number,
        },
        rfid:{
            type:Number,
            default: -1,
        },
        show:{
            type:Boolean,
            default:true
        },
        icon:{
            type:String,
            default:""
        },
        buttonStyle:{
            type:String,
            default:""
        },
        fileLisToRight:{
            type:Boolean,
            default:false,
        }
    },
    data() {
        return {
            uploadDisabled:false,
            upload: {
                // 是否显示弹出层（用户导入）
                open:true,
                // 弹出层标题（用户导入）
                title: '',
                // 是否禁用上传
                isUploading: false,
                // 是否更新已经存在的用户数据
                updateSupport: 0,
                // 设置上传的请求头部
                // 上传的地址
                url: 'http:',
                fileList: [],
                fileName: []
            }
        };
    },
    mounted() {

    },
    methods: {
        fileRemove(){
            this.upload.fileList = []
            this.uploadDisabled = false
        },
        fileUpload(){
            console.log("upload")
            console.log(this.id)
            let fd = new FormData()
            // 也可以传非文件的数据，例如
            if (this.rfid == -1 ) {
                fd.append("id",JSON.stringify(this.id))
            } else {
                fd.append("id",JSON.stringify(this.rfid))
            }

            // 只需要在后端接收一下即可(注意)
            for(let i in this.upload.fileList){
                fd.append('file',this.upload.fileList[i].raw)
            }
            console.log(this.upload.fileList.length)
            this.axios({
                method:"post",
                url:this.url,
                data:fd,
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(resp =>{
                //设置表格数据
                console.log(resp.data)
                // console.log(resp.data.id)
                this.upload.fileList=[]
            })
        },
        // 移除封面图
        handleRemove(file, fileList) {
            fileList,
                console.log(file);
            setTimeout(() => {
                this.uploadDisabled = false;
            }, 1000);
        },
        // 文件状态变化
        handleChange(file, fileList) { //上传成功与否都会触发
            this.uploadDisabled = fileList.length >= this.limit;
        },
        // 预览封面图
        handlePictureCardPreview(file) {
            this.form.dialogImageUrl = file.url;
            this.uploadDisabled = true;
        },
        //上传成功
        handleUploadSuccess(file) {
            console.log('上传成')
            this.form.dialogImageUrl = file.result; //上传服务器成功
            this.uploadDisabled = true;
        },
        // 上传发生变化钩子
        handleFileChange(file, fileList) {
            this.upload.fileList = fileList;
        },
        // 删除之前钩子
        handleFileRemove(file, fileList) {
            this.upload.fileList = fileList;
        },
    }
}
</script>
