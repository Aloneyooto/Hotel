# 酒店管理系统
## 一级路由 /superadmin
### 增加房间

url : `/addroom`

type : `POST`

接收数据:

roomStr : 房间信息的json字符串

fileList : 图片文件流

返回数据:

RoomExecution对象 ：含有标识码和处理结果信息字符串

### 根据房间号查找房间

url : `/getroombyid`

type : GET

接收数据:

roomId : 房间号

返回数据：

RoomExecution对象 ：含有标识码和处理结果信息字符串

成功时含有查到的Room对象

### 根据条件查找房间

这个方法可能会出现问题

url : `/getroomlist`

type : GET

接收数据:

含有如下信息的json字符串

pageIndex: 页码

pageSize: 页面大小（每一页显示记录的条数）

roomType : 房间类型

roomState : 房间状态

返回数据：

RoomExecution对象 ：含有标识码和处理结果信息字符串

成功时含有查到的Room列表

### 修改房间

url : `/modifyroom`

type : POST

接收数据:

roomStr : 房间信息的json字符串

fileList : 图片文件流

返回数据:

RoomExecution对象 ：含有标识码和处理结果信息字符串

### 3.13更新职位信息 一级路由一样
### 增加职位

url: '/addposition'

type : `POST`

接收数据:

position对象

返回数据:

PositionExecution对象(和RoomExecution一样)

### 根据id查询职位

url : `/querypositionbyid`

type : `GET`

接收数据: 

positionId ：职位ID

返回数据:

PositionExecution对象

### 根据职位名称和备注查询职位 可模糊查询

url : `querypositionlist`

type : `GET`

接收数据:

positionName:职位名称字符串

positionNote:职位备注字符串

pageIndex：页码

pageSize:页面大小

返回数据：

PositionExecution对象（内含查询到的列表和该条件查询记录总数）

### 修改职位

url : `modifyposition`

type : `POST`

接收数据:

position对象

返回数据:

PositionExecution对象

### 删除职位

url : `deleteposition`

type : `POST`

接收数据:

positionId 职位id

返回数据:

PositionExecution对象

### Recreation
### 增加其他娱乐项目列表

url: '/addrecreation'

type : `POST`

接收数据:

recreation对象

返回数据:

RecreationExecution对象(和RoomExecution一样)

### 根据Id查询其他娱乐项目信息

url : `/queryrecreationbyid`

type : `GET`

接收数据: 

recreationId ：职位ID

返回数据:

RecreationExecution对象

### 根据娱乐项目名称查询项目信息 可模糊查询

url : `queryrecreationlist`

type : `GET`

接收数据:

recreationName:职位名称字符串

pageIndex：页码

pageSize:页面大小

返回数据：

RecreationExecution对象（内含查询到的列表和该条件查询记录总数）

### 修改娱乐项目信息

url : `modifyrecreation`

type : `POST`

接收数据:

Recreation对象

返回数据:

RecreationExecution对象

### 删除其他娱乐项目

url : `deleterecreation`

type : `POST`

接收数据:

recreationId 职位id

返回数据:

RecreationExecution对象

## 一级路由 /personel
### 增加员工

url: `/addemployee`

接收数据：

employeeStr : 员工信息字符串

cardImg : 身份证图片

faceImg : 面部图片

返回数据:

EmployeeExecution处理结果对象

### 查询id对应的员工信息

url : `/getemployeebyid`

接收数据:

employeeId : 工号

返回数据:

EmployeeExecution处理结果对象

成员变量employee为查询结果

### 查询员工列表

url : `/getemployeelist`

接收数据:

Employee employeeCondition : 查询条件

pageIndex : 页码

pageSize : 页面大小

返回数据:

返回数据:

EmployeeExecution处理结果对象

成员变量employeeList为查询结果

### 修改员工信息

url : `/updateemployee`

接收数据:

employeeStr : 员工信息字符串

cardImg : 身份证图片

faceImg : 面部图片

返回数据:

EmployeeExecution处理结果对象

### 删除员工信息

url : `/deleteemployee`

接收数据:

employeeId : 员工工号

返回数据:

EmployeeExecution处理结果对象

### 增加清洁员楼层信息

url : `/addcleaner`

接收数据:

Cleaner cleaner

返回数据:

CleanerExecution

### 通过id查询清洁员

url : `/querycleanerbyid`

接收数据:

String cleanerId

返回数据:

CleanerExecution

### 查询清洁员信息列表

url : `/querycleanerlist`

接收数据:

String roomFloor

int pageIndex

int pageSize

返回数据:

CleanerExecution

### 修改清洁员信息

url : `/modifycleaner`

接收数据:

Cleaner cleaner

返回数据:

CleanerExecution

### 删除清洁员信息

url : `/deletecleaner`

接收数据:

String cleanerId

返回数据：

CleanerExecution
