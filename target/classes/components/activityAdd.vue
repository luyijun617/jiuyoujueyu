<template>
    <div>
        {{activityInfo.mallName}}
      <template v-if="activityInfo.buildingzoneName">
        - {{activityInfo.buildingzoneName}}
      </template>
      <template v-if="activityInfo.floorCustomName">
        - {{activityInfo.floorCustomName}}
      </template>
        - {{activityInfo.shopNo}}
      <!--新增-->
      <el-form :model="activityInfo"  ref="activityInfo"  label-width="100px" label-position="left" :rules="rules">
        <el-form-item label="活动名称" prop="activityName">
          <el-input v-model.trim="activityInfo.activityName" style="width: 250px"/>
        </el-form-item>
        <el-form-item label="品牌名称" prop="brandName">
          <el-input v-model.trim="activityInfo.brandName" style="width: 250px"/>
        </el-form-item>
        <el-form-item label="品类(业态)" prop="businesstypeCode">
          <el-cascader style="width: 250px" clearable placeholder="品类(业态)"
            expand-trigger="hover"
            :options="businessTypeInfoList"
            v-model="activityInfo.businesstypeCode"
            @change="handleChange">
          </el-cascader>
        </el-form-item>
        <el-form-item label="活动类型" prop="activityPurpose">
          <el-select placeholder="活动类型" v-model="activityInfo.activityPurpose" clearable style="width: 250px">
            <el-option v-for="item in this.storePurposeList" :key="item.purposeId" :label="item.purposeName" :value="item.purposeId" />
          </el-select>
        </el-form-item>
        <el-form-item label="活动时间"  prop="beginEndDate" clearable >
          <el-date-picker
            v-model="activityInfo.beginEndDate"
            type="daterange"
            value-format="yyyy-MM-dd"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动点评" prop="activityComment">
          <el-input v-model.trim="activityInfo.activityComment"  type="textarea" style="width: 450px"></el-input>
        </el-form-item>
        <el-form-item label="列表图" prop="acticityListPic">
          <el-upload
            class="avatar-uploader"
            accept="image/png,image/jpeg"
            action="/appServer/file/upload/mallListPic"
            :show-file-list="false"
            :on-success="handleActivityListPicSuccess">
            <img v-if="acticityListPic != null || activityInfo.acticityListPic != null"
                 :src="acticityListPic == null ? getImageUrl(activityInfo.acticityListPic) : acticityListPic " class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="焦点图">
          <el-upload
          accept="image/png,image/jpeg"
          action="/appServer/file/upload/mallFocusPic"
          :multiple="true"
          :on-success="handleActivityPicSuccess"
          :on-remove="handleActivityPicRemove"
          :file-list="activityPicList"
          list-type="picture-card">

          <i class="el-icon-plus"></i>

          </el-upload>
        </el-form-item>
      </el-form>
      <el-form>
        <el-form-item>
          <el-button type="primary" @click="submitForm('activityInfo')">保存</el-button>
          <el-button @click="cancel()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
import pptApi from '../../../api/pptApi.js'
import activityTypeSelector from './activityTypeSelector.vue'
import pptShopApi from '../../../api/pptShopApi.js'
import commonApi from '../../../api/commonApi.js'
export default {
  name: 'activityAdd.vue',
  components: {
    activityTypeSelector
  },
  props: {
    activityForm: Object
  },
  data () {
    return {
      activityPicList: [],
      storePurposeList: [],
      businessTypeInfoList: null,
      acticityListPic: null,
      acticityPic: [],

      // activityInfo: {
      //   mallName: null,
      //   mallCode: null,
      //   buildingzoneName: null,
      //   buildingzoneCode: null,
      //   floorCustomName: null,
      //   floorCode: null,
      //   shopName: null,
      //   shopCode: null,
      //   shopNo: null,
      //   shopPic: null,
      //   activityId: null,
      //   activityName: null,
      //   brandName: null,
      //   brandCode: null,
      //   businesstypeCode: null,
      //   activityPurpose: null,
      //   beginDate: null,
      //   endDate: null,
      //   activityComment: null,
      //   acticityListPic: null,
      //   acticityPic: null,
      //   sortOrder: null,
      //   acticityMiddleId: null,
      //   lastModifyId: null,
      //   lastModifyTime: null,
      //   customCrtId: null,
      //   customCrtTime: null,
      //   beginEndDate: null
      // },
      activityInfo: {
        mallName: '三号湾广场',
        mallCode: 'M000031',
        buildingzoneName: 'M00003102',
        buildingzoneCode: '右幢',
        floorCustomName: '1F',
        floorCode: 'M00003102001',
        shopName: '紧急招租紧急招租',
        shopCode: 'M00003102001001',
        shopNo: 'H00004',
        shopPic: '/paas/img/mall/1/M000031/M00003102/M00003102001/M00003102001001/2019/08/28/15669757245797090.jpg',
        activityId: '123212',
        activityName: 'aaaaaaa',
        brandName: 'aaa',
        brandCode: '999',
        businesstypeCode: '300',
        activityPurpose: '2',
        beginDate: '2019-08-01',
        endDate: '2019-09-30',
        activityComment: 'aaaaaaaaaaaaaaaaaaaaaaaaa',
        acticityListPic: '/data/img/activity/2019/08/30/15671462266594516.jpg',
        acticityPic: '/data/img/activity/2019/08/30/15671462267339519.jpg',
        sortOrder: '0',
        acticityMiddleId: null,
        lastModifyId: null,
        lastModifyTime: null,
        customCrtId: null,
        customCrtTime: null,
        beginEndDate: null
      },
      activityMiddle: {
        recId: null,
        activityId: null,
        activityName: null,
        shopCode: null,
        brandName: null,
        brandCode: null,
        businesstypeCode: null,
        activityPurpose: null,
        beginDate: null,
        endDate: null,
        activityComment: null,
        acticityListPic: null,
        acticityPic: null,
        applyStatus: null,
        rejectMsg: null
      },
      queryParam: {
        pageNo: 1,
        pageSize: 20,
        orderBy: null,
        orderDir: null,
        searchMap: {
          acticityMiddleId: null
        }
      },
      rules: {
        mallName: [{required: true, message: '商业体为必填项', trigger: 'change'}],
        shopName: [{required: true, message: '店铺名称为必填项', trigger: 'change'}],
        activityId: [{required: true, message: '活动编号为必填项', trigger: 'change'}],
        shopListPic: [{required: true, message: '照片信息为必填项', trigger: 'change'}],
        activityName: [{required: true, message: '活动名称为必填项', trigger: 'change'}],
        brandName: [{required: true, message: '品牌名称为必填项', trigger: 'change'}],
        businesstypeCode: [{required: true, message: '品类(业态)为必填项', trigger: 'change'}],
        activityPurpose: [{required: true, message: '活动类型为必填项', trigger: 'change'}],
        beginEndDate: [{required: true, message: '活动时间为必填项', trigger: 'change'}],
        activityComment: [{required: true, message: '活动点评为必填项', trigger: 'change'}],
        acticityListPic: [{required: true, message: '列表图为必填项', trigger: 'change'}],
        activityPic: [{required: true, message: '焦点图为必填项', trigger: 'change'}]
      }
    }
  },
  mounted () {
    this.initInfo()
    this.init()
  },
  methods: {
    init () {
      this.getStorePurpose()
      this.businessTypeInfoInfoList()
      this.setActicityPicList()
    },
    initInfo () {
      // this.activityInfo = JSON.parse(JSON.stringify(this.activityForm))
      this.$set(this.activityInfo, 'beginEndDate', null)
      if (this.activityInfo.beginDate != null && this.activityInfo.endDate != null) {
        this.activityInfo.beginEndDate = [this.activityInfo.beginDate, this.activityInfo.endDate]
      }
    },
    getStorePurpose () {
      pptShopApi.getStorePurpose().then(response => {
        if (response.errorCode === 1) {
          this.storePurposeList = response.data
        } else {
          this.$message.error(response.errorContent)
        }
      })
    },
    businessTypeInfoInfoList () {
      commonApi.getBusinessTypeInfo().then(response => {
        if (response.errorCode === 1) {
          this.businessTypeInfoList = response.data
        } else {
          this.$message.error(response.errorContent)
        }
      })
    },
    handleChange (value) {
      if (value != null && value.length === 3) {
        this.activityInfo.businesstypeCode = value[2]
      } else {
        this.activityInfo.businesstypeCode = null
      }
    },
    setActicityPicList () {
      if (this.activityInfo.acticityPic != null && this.activityInfo.acticityPic !== '') {
        var acticityPicFocusArray = this.activityInfo.acticityPic.split(',')
        var mfLength = acticityPicFocusArray.length
        for (var i = 0; i < mfLength; i++) {
          this.activityPicList.push({'name': i, 'url': this.getImageUrl(acticityPicFocusArray[i])})
          this.acticityPic.push(acticityPicFocusArray[i])
        }
      }
    },

    submitForm (formName) {
      if (this.activityInfo.activityPurpose != null && this.activityInfo.purposeName !== '') {
        this.storePurposeList.filter(item => {
          if (item.purposeId === this.activityInfo.activityPurpose) {
            this.activityInfo.activityPurpose = item.purposeId
            this.activityInfo.purposeName = item.purposeName
          }
        })
      } else {
        this.activityInfo.activityPurpose = ''
        this.activityInfo.purposeName = ''
      }

      if (this.activityInfo.businesstypeCode == null || this.activityInfo.businesstypeCode === '') {
        this.activityInfo.businesstypeCode = ''
        this.activityInfo.businesstypeName = ''
      }
      this.activityInfo.acticityPic = this.acticityPic.toString()
      if (this.activityInfo.beginEndDate != null && this.activityInfo.beginEndDate.length === 2) {
        this.activityInfo.beginDate = this.activityInfo.beginEndDate[0]
        this.activityInfo.endDate = this.activityInfo.beginEndDate[1]
      }

      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.activityInfo.acticityMiddleId != null && this.activityInfo.acticityMiddleId !== 0) {
            this.activityMiddle.recId = this.activityInfo.acticityMiddleId
            this.activityMiddle.activityId = this.activityInfo.activityId
            this.activityMiddle.activityName = this.activityInfo.activityName
            this.activityMiddle.shopCode = this.activityInfo.shopCode
            this.activityMiddle.brandName = this.activityInfo.brandName
            this.activityMiddle.brandCode = this.activityInfo.brandCode
            this.activityMiddle.businesstypeCode = this.activityInfo.businesstypeCode
            this.activityMiddle.activityPurpose = this.activityInfo.activityPurpose
            this.activityMiddle.beginDate = this.activityInfo.beginDate
            this.activityMiddle.endDate = this.activityInfo.endDate
            this.activityMiddle.activityComment = this.activityInfo.activityComment
            this.activityMiddle.acticityListPic = this.activityInfo.acticityListPic
            this.activityMiddle.acticityPic = this.activityInfo.acticityPic
            this.activityMiddle.applyStatus = 0

            // 审核通过、审核不通过修改时，新增记录
            if (this.activityInfo.applyStatus === 2 || this.activityInfo.applyStatus === 3) {
              this.activityMiddle.recId = null
            } else {
              // 修改记录
              this.activityMiddle.recId = this.activityInfo.acticityMiddleId
            }

            pptApi.submitActivityMiddle(this.activityMiddle).then(response => {
              if (response.errorCode === 1) {
                this.$message({
                  type: 'success',
                  message: '操作成功!'
                })
                this.$emit('closePage', true)
              } else {
                this.$message.error(response.errorContent)
              }
            })
          } else {
            pptApi.saveActivity(this.activityInfo).then(response => {
              if (response.errorCode === 1) {
                this.$message({
                  type: 'success',
                  message: '操作成功!'
                })
                this.$emit('closePage', true)
              } else {
                this.$message.error(response.errorContent)
              }
            })
          }
        }
      })
    },
    cancel () {
      this.$emit('closePage', false)
    },
    handleActivityListPicSuccess (res, file) {
      this.acticityListPic = URL.createObjectURL(file.raw)
      this.activityInfo.acticityListPic = res.result.filePath
    },
    handleActivityPicSuccess (res, file) {
      this.acticityPic.push(res.result.filePath)
    },
    handleActivityPicRemove (file, fileList) {
      let length = this.acticityPic.length
      if (length > 0) {
        for (let i = 0; i < length; i++) {
          if (file.url.indexOf(this.acticityPic[i]) !== -1) {
            this.acticityPic.splice(i, 1)
            return
          }
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
