-- H2 初始化数据 (MySQL兼容模式)
MERGE INTO stores (id, store_code, store_name, address, contact_phone, daily_profit_target, monthly_profit_target, status) KEY(id) VALUES
(1, 'ST001', '朝阳旗舰店', '北京市朝阳区建国路88号', '010-88886666', 5000.00, 150000.00, 1),
(2, 'ST002', '海淀分店', '北京市海淀区中关村大街66号', '010-66668888', 3000.00, 90000.00, 1);

MERGE INTO ingredient_categories (id, category_code, category_name, parent_id, sort_order) KEY(id) VALUES
(1, 'MEAT', '肉类', 0, 1),
(2, 'SEAFOOD', '海鲜类', 0, 2),
(3, 'VEG', '蔬菜类', 0, 3),
(4, 'FRUIT', '水果类', 0, 4),
(5, 'COND', '调料类', 0, 5),
(6, 'GRAIN', '粮油类', 0, 6),
(7, 'DAIRY', '乳制品', 0, 7),
(8, 'BVG', '饮料酒水', 0, 8),
(9, 'BEEF', '牛肉', 1, 1),
(10, 'PORK', '猪肉', 1, 2),
(11, 'CHICKEN', '禽肉', 1, 3),
(12, 'FISH', '鱼类', 2, 1),
(13, 'SHRIMP', '虾类', 2, 2),
(14, 'LEAFY', '叶菜类', 3, 1),
(15, 'ROOT', '根茎类', 3, 2);

MERGE INTO ingredients (id, store_id, category_id, ingredient_code, ingredient_name, unit, unit_price, safety_stock, max_stock, shelf_life_days, warning_days, status) KEY(id) VALUES
(1, 1, 9,  'BEEF01',  '牛腱子肉',  'kg', 68.00, 20, 80, 3, 1, 1),
(2, 1, 9,  'BEEF02',  '牛腩肉',     'kg', 58.00, 15, 60, 3, 1, 1),
(3, 1, 9,  'BEEF03',  '肥牛卷',     'kg', 78.00, 10, 50, 2, 1, 1),
(4, 1, 10, 'PORK01',  '五花肉',     'kg', 32.00, 20, 80, 3, 1, 1),
(5, 1, 10, 'PORK02',  '猪里脊',     'kg', 36.00, 15, 60, 3, 1, 1),
(6, 1, 10, 'PORK03',  '排骨',       'kg', 42.00, 10, 40, 3, 1, 1),
(7, 1, 11, 'CHICK01', '鸡腿肉',     'kg', 22.00, 20, 80, 3, 1, 1),
(8, 1, 11, 'CHICK02', '鸡翅中',     'kg', 28.00, 15, 60, 3, 1, 1),
(9, 1, 11, 'CHICK03', '整鸡',       '只', 35.00, 10, 30, 2, 1, 1),
(10, 1, 12, 'FISH01', '鲈鱼',       '条', 25.00, 10, 40, 1, 1, 1),
(11, 1, 12, 'FISH02', '三文鱼',     'kg', 120.00, 5, 20, 2, 1, 1),
(12, 1, 12, 'FISH03', '带鱼',       'kg', 28.00, 10, 40, 2, 1, 1),
(13, 1, 13, 'SHRIMP01','基围虾',    'kg', 55.00, 8, 30, 1, 1, 1),
(14, 1, 13, 'SHRIMP02','虾仁',      'kg', 65.00, 5, 20, 2, 1, 1),
(15, 1, 14, 'LEAF01',  '娃娃菜',    'kg', 4.50, 10, 40, 2, 1, 1),
(16, 1, 14, 'LEAF02',  '生菜',      'kg', 5.00, 10, 40, 1, 1, 1),
(17, 1, 14, 'LEAF03',  '菠菜',      'kg', 6.00, 8, 30, 1, 1, 1),
(18, 1, 15, 'ROOT01',  '土豆',      'kg', 3.00, 30, 100, 7, 1, 1),
(19, 1, 15, 'ROOT02',  '胡萝卜',    'kg', 4.00, 20, 80, 7, 1, 1),
(20, 1, 15, 'ROOT03',  '白萝卜',    'kg', 3.50, 15, 60, 7, 1, 1),
(21, 1, 15, 'ROOT04',  '莲藕',      'kg', 8.00, 10, 40, 5, 1, 1);

MERGE INTO dish_categories (id, category_code, category_name, sort_order) KEY(id) VALUES
(1, 'HOT',    '热菜',  1),
(2, 'COLD',   '凉菜',  2),
(3, 'SOUP',   '汤类',  3),
(4, 'BBQ',    '烧烤',  4),
(5, 'SUSHI',  '寿司',  5),
(6, 'DESSERT','甜品',  6),
(7, 'FRUIT',  '水果',  7),
(8, 'DRINK',  '饮品',  8);

MERGE INTO dishes (id, store_id, category_id, dish_code, dish_name, cost_price, sale_price, is_buffet, status, priority_score) KEY(id) VALUES
(1, 1, 1, 'HOT01', '红烧牛腩',     22.00, 0, 1, 1, 90),
(2, 1, 1, 'HOT02', '黑椒牛柳',     25.00, 0, 1, 1, 85),
(3, 1, 1, 'HOT03', '糖醋里脊',     12.00, 0, 1, 1, 88),
(4, 1, 1, 'HOT04', '宫保鸡丁',     10.00, 0, 1, 1, 82),
(5, 1, 1, 'HOT05', '水煮鱼',       18.00, 0, 1, 1, 80),
(6, 1, 1, 'HOT06', '蒜蓉粉丝蒸虾',  20.00, 0, 1, 1, 92),
(7, 1, 1, 'HOT07', '孜然牛肉',     24.00, 0, 1, 1, 75),
(8, 1, 1, 'HOT08', '可乐鸡翅',     8.00, 0, 1, 1, 78),
(9, 1, 1, 'HOT09', '红烧排骨',     16.00, 0, 1, 1, 86),
(10, 1, 2, 'COLD01','凉拌黄瓜',     3.00, 0, 1, 1, 70),
(11, 1, 2, 'COLD02','口水鸡',      12.00, 0, 1, 1, 65),
(12, 1, 2, 'COLD03','凉拌木耳',     5.00, 0, 1, 1, 60),
(13, 1, 3, 'SOUP01','番茄蛋汤',     3.00, 0, 1, 1, 75),
(14, 1, 3, 'SOUP02','冬瓜排骨汤',   8.00, 0, 1, 1, 72),
(15, 1, 3, 'SOUP03','酸辣汤',       4.00, 0, 1, 1, 68),
(16, 1, 4, 'BBQ01', '烤羊排',      30.00, 0, 1, 1, 95),
(17, 1, 4, 'BBQ02', '烤鸡翅',       6.00, 0, 1, 1, 80),
(18, 1, 4, 'BBQ03', '烤鱿鱼',      10.00, 0, 1, 1, 78),
(19, 1, 5, 'SUSHI01','三文鱼寿司',  15.00, 0, 1, 1, 90),
(20, 1, 5, 'SUSHI02','加州卷',      10.00, 0, 1, 1, 75),
(21, 1, 6, 'DES01', '提拉米苏',     8.00, 0, 1, 1, 70),
(22, 1, 6, 'DES02', '芒果布丁',     5.00, 0, 1, 1, 65),
(23, 1, 7, 'FRU01', '时令水果拼盘', 6.00, 0, 1, 1, 72);

-- 销售数据（近7天）
MERGE INTO sales_records (id, store_id, business_date, total_customers, total_amount, buffet_amount, single_amount) KEY(id) VALUES
(1, 1, DATEADD('DAY', -6, CURRENT_DATE), 286, 17160.00, 15730.00, 1430.00),
(2, 1, DATEADD('DAY', -5, CURRENT_DATE), 312, 18720.00, 17160.00, 1560.00),
(3, 1, DATEADD('DAY', -4, CURRENT_DATE), 298, 17880.00, 16390.00, 1490.00),
(4, 1, DATEADD('DAY', -3, CURRENT_DATE), 345, 20700.00, 18975.00, 1725.00),
(5, 1, DATEADD('DAY', -2, CURRENT_DATE), 320, 19200.00, 17600.00, 1600.00),
(6, 1, DATEADD('DAY', -1, CURRENT_DATE), 301, 18060.00, 16555.00, 1505.00),
(7, 1, CURRENT_DATE, 0, 0.00, 0.00, 0.00);

-- 盈利目标
MERGE INTO profit_targets (id, store_id, target_year, target_month, monthly_target, daily_target, status) KEY(id) VALUES
(1, 1, YEAR(CURRENT_DATE), MONTH(CURRENT_DATE), 150000.00, 5000.00, 0),
(2, 2, YEAR(CURRENT_DATE), MONTH(CURRENT_DATE), 90000.00, 3000.00, 0);

-- 库存记录
MERGE INTO inventory (id, store_id, ingredient_id, batch_no, quantity, unit_cost, production_date, expiry_date, status) KEY(id) VALUES
(1, 1, 1,  'BATCH20250515', 30.0, 68.00, DATEADD('DAY', -7, CURRENT_DATE), DATEADD('DAY', 1, CURRENT_DATE), 1),
(2, 1, 2,  'BATCH20250516', 25.0, 58.00, DATEADD('DAY', -6, CURRENT_DATE), DATEADD('DAY', 2, CURRENT_DATE), 1),
(3, 1, 4,  'BATCH20250518', 40.0, 32.00, DATEADD('DAY', -4, CURRENT_DATE), DATEADD('DAY', 4, CURRENT_DATE), 1),
(4, 1, 5,  'BATCH20250518', 40.0, 36.00, DATEADD('DAY', -4, CURRENT_DATE), DATEADD('DAY', 4, CURRENT_DATE), 1),
(5, 1, 7,  'BATCH20250519', 35.0, 22.00, DATEADD('DAY', -3, CURRENT_DATE), DATEADD('DAY', 5, CURRENT_DATE), 1),
(6, 1, 18, 'BATCH20250518', 60.0,  3.00, DATEADD('DAY', -4, CURRENT_DATE), DATEADD('DAY', 4, CURRENT_DATE), 1);

-- 食材消耗
MERGE INTO ingredient_consumption (id, store_id, business_date, ingredient_id, consumption_qty, consumption_cost) KEY(id) VALUES
(1, 1, DATEADD('DAY', -1, CURRENT_DATE), 2, 8.0, 464.00),
(2, 1, DATEADD('DAY', -1, CURRENT_DATE), 18, 15.0, 45.00),
(3, 1, DATEADD('DAY', -1, CURRENT_DATE), 13, 6.0, 330.00),
(4, 1, DATEADD('DAY', -1, CURRENT_DATE), 5, 10.0, 360.00);

-- 菜品配方
MERGE INTO dish_recipes (id, dish_id, ingredient_id, quantity, unit, is_main) KEY(id) VALUES
(1, 1, 2, 0.5, 'kg', 1),
(2, 1, 18, 0.3, 'kg', 0),
(3, 1, 19, 0.1, 'kg', 0),
(4, 6, 13, 0.3, 'kg', 1);
