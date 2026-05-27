# 小徐同学 - 餐饮智能管理系统

自助餐行业智能管理平台，与收银系统深度绑定，实现智能采购预测、库存预警、菜品管理和盈利分析。

## 功能模块

| 模块 | 说明 |
|------|------|
| 📊 **数据大屏** | 实时展示经营指标：用餐人数、收入、库存状态、预警、热销菜品 |
| 📦 **库存管理** | 入库/出库/盘点/报废管理，保质期预警 |
| 📋 **采购建议** | AI智能预测次日食材采购需求，自动生成采购清单 |
| 🍽️ **菜品管理** | 上下架管理、配方管理、智能上下架推荐 |
| 📈 **报表中心** | 日报、月报、成本分析、盈利分析、损耗分析 |
| 💰 **点单收银** | 自助点单、自定义菜品加菜、价格调整、收银结账 |

## 技术栈

```
后端: Spring Boot 3.2 + JPA + H2/MySQL + Redis
前端: Vue 3 + Element Plus + ECharts + Pinia
部署: Docker + Docker Compose + Nginx
```

## 快速启动

### 方式一：Standalone（推荐，无需外部依赖）

仅需 Java 17+，无需 MySQL / Redis：

```bash
cd backend
mvn clean package -DskipTests
java -jar target/restaurant-management-1.0.0.jar --spring.profiles.active=standalone
```

浏览器打开 **http://localhost:3000**

### 方式二：Docker 部署

```bash
docker compose up -d
```

浏览器打开 **http://localhost**

### 方式三：开发模式

```bash
# 终端 1：启动后端（使用 H2 开发数据库）
cd backend && mvn spring-boot:run -Dspring.profiles.active=dev

# 终端 2：启动前端
cd frontend && npm install && npm run dev

# 浏览器打开 http://localhost:5173
```

## V2 新增功能

- 🆕 点单支持 **自定义菜品加菜** — 手动输入菜名和价格
- 🆕 购物车内 **直接编辑菜品单价** — 点价格数字即可修改
- 🆕 清空购物车弹窗确认，防误操作
- 🛠️ 修复 API 代理问题，前端可直接调用后端接口

## 项目结构

```
restaurant-management/
├── backend/                    # Spring Boot 后端
│   ├── src/main/java/          # Java 源码
│   │   ├── common/             # Result, PageResult
│   │   ├── config/             # WebConfig, FrontendServer
│   │   ├── controller/         # REST 控制器
│   │   ├── dto/                # 数据传输对象
│   │   ├── entity/             # JPA 实体
│   │   ├── exception/          # 全局异常处理
│   │   ├── repository/         # JPA Repository
│   │   └── service/            # 业务逻辑层
│   └── src/main/resources/     # 配置文件, SQL
├── frontend/                   # Vue 3 前端
│   └── src/
│       ├── api/                # Axios API 封装
│       ├── layouts/            # 布局组件
│       ├── router/             # Vue Router
│       └── views/              # 页面组件
├── database/                   # SQL 脚本
├── docker-compose.yml          # Docker 编排
└── README.md
```
