-- ============================================================
-- 餐饮智能管理系统 - 示例数据 (H2兼容版)
-- ============================================================

-- 门店
INSERT INTO stores (store_code, store_name, address, contact_phone, daily_profit_target, monthly_profit_target) VALUES
('ST001', '朝阳旗舰店', '北京市朝阳区建国路88号', '010-88886666', 5000.00, 150000.00),
('ST002', '海淀分店', '北京市海淀区中关村大街66号', '010-66668888', 3000.00, 90000.00);

-- 食材分类
INSERT INTO ingredient_categories (category_code, category_name, parent_id, sort_order) VALUES
('MEAT', '肉类', 0, 1),
('SEAFOOD', '海鲜类', 0, 2),
('VEG', '蔬菜类', 0, 3),
('FRUIT', '水果类', 0, 4),
('COND', '调料类', 0, 5),
('GRAIN', '粮油类', 0, 6),
('DAIRY', '乳制品', 0, 7),
('BVG', '饮料酒水', 0, 8),
('BEEF', '牛肉', 1, 1),
('PORK', '猪肉', 1, 2),
('CHICKEN', '禽肉', 1, 3),
('FISH', '鱼类', 2, 1),
('SHRIMP', '虾类', 2, 2),
('LEAFY', '叶菜类', 3, 1),
('ROOT', '根茎类', 3, 2);

-- 食材 (门店1 - 朝阳旗舰店)
INSERT INTO ingredients (store_id, category_id, ingredient_code, ingredient_name, unit, unit_price, safety_stock, max_stock, shelf_life_days, warning_days, status) VALUES
(1, 9,  'BEEF01',  '牛腱子肉',  'kg', 68.00, 20, 80, 3, 1, 1),
(1, 9,  'BEEF02',  '牛腩肉',     'kg', 58.00, 15, 60, 3, 1, 1),
(1, 9,  'BEEF03',  '肥牛卷',     'kg', 78.00, 10, 50, 2, 1, 1),
(1, 10, 'PORK01',  '五花肉',     'kg', 32.00, 20, 80, 3, 1, 1),
(1, 10, 'PORK02',  '猪里脊',     'kg', 36.00, 15, 60, 3, 1, 1),
(1, 10, 'PORK03',  '排骨',       'kg', 42.00, 10, 40, 3, 1, 1),
(1, 11, 'CHICK01', '鸡腿肉',     'kg', 22.00, 20, 80, 3, 1, 1),
(1, 11, 'CHICK02', '鸡翅中',     'kg', 28.00, 15, 60, 3, 1, 1),
(1, 11, 'CHICK03', '整鸡',       '只', 35.00, 10, 30, 2, 1, 1),
(1, 12, 'FISH01',  '鲈鱼',       '条', 25.00, 10, 40, 1, 1, 1),
(1, 12, 'FISH02',  '三文鱼',     'kg', 120.00, 5, 20, 2, 1, 1),
(1, 12, 'FISH03',  '带鱼',       'kg', 28.00, 10, 40, 2, 1, 1),
(1, 13, 'SHRIMP01','基围虾',     'kg', 55.00, 8, 30, 1, 1, 1),
(1, 13, 'SHRIMP02','虾仁',       'kg', 65.00, 5, 20, 2, 1, 1),
(1, 14, 'LEAF01',  '娃娃菜',     'kg', 4.50, 10, 40, 2, 1, 1),
(1, 14, 'LEAF02',  '生菜',       'kg', 5.00, 10, 40, 1, 1, 1),
(1, 14, 'LEAF03',  '菠菜',       'kg', 6.00, 8, 30, 1, 1, 1),
(1, 15, 'ROOT01',  '土豆',       'kg', 3.00, 30, 100, 7, 1, 1),
(1, 15, 'ROOT02',  '胡萝卜',     'kg', 4.00, 20, 80, 7, 1, 1),
(1, 15, 'ROOT03',  '白萝卜',     'kg', 3.50, 15, 60, 7, 1, 1),
(1, 15, 'ROOT04',  '莲藕',       'kg', 8.00, 10, 40, 5, 1, 1);

-- 菜品分类
INSERT INTO dish_categories (category_code, category_name, sort_order) VALUES
('HOT',    '热菜',  1),
('COLD',   '凉菜',  2),
('SOUP',   '汤类',  3),
('BBQ',    '烧烤',  4),
('SUSHI',  '寿司',  5),
('DESSERT','甜品',  6),
('FRUIT',  '水果',  7),
('DRINK',  '饮品',  8);

-- 菜品 (门店1)
INSERT INTO dishes (store_id, category_id, dish_code, dish_name, cost_price, sale_price, is_buffet, status, priority_score) VALUES
(1, 1, 'HOT01', '红烧牛腩',     22.00, 0, 1, 1, 90),
(1, 1, 'HOT02', '黑椒牛柳',     25.00, 0, 1, 1, 85),
(1, 1, 'HOT03', '糖醋里脊',     12.00, 0, 1, 1, 88),
(1, 1, 'HOT04', '宫保鸡丁',     10.00, 0, 1, 1, 82),
(1, 1, 'HOT05', '水煮鱼',       18.00, 0, 1, 1, 80),
(1, 1, 'HOT06', '蒜蓉粉丝蒸虾',  20.00, 0, 1, 1, 92),
(1, 1, 'HOT07', '孜然牛肉',     24.00, 0, 1, 1, 75),
(1, 1, 'HOT08', '可乐鸡翅',      8.00, 0, 1, 1, 78),
(1, 1, 'HOT09', '红烧排骨',     16.00, 0, 1, 1, 86),
(1, 2, 'COLD01','凉拌黄瓜',      3.00, 0, 1, 1, 70),
(1, 2, 'COLD02','口水鸡',       12.00, 0, 1, 1, 65),
(1, 2, 'COLD03','凉拌木耳',      5.00, 0, 1, 1, 60),
(1, 3, 'SOUP01','番茄蛋汤',      3.00, 0, 1, 1, 75),
(1, 3, 'SOUP02','冬瓜排骨汤',    8.00, 0, 1, 1, 72),
(1, 3, 'SOUP03','酸辣汤',        4.00, 0, 1, 1, 68),
(1, 4, 'BBQ01', '烤羊排',       30.00, 0, 1, 1, 95),
(1, 4, 'BBQ02', '烤鸡翅',        6.00, 0, 1, 1, 80),
(1, 4, 'BBQ03', '烤鱿鱼',       10.00, 0, 1, 1, 78),
(1, 5, 'SUSHI01','三文鱼寿司',   15.00, 0, 1, 1, 90),
(1, 5, 'SUSHI02','加州卷',       10.00, 0, 1, 1, 75),
(1, 6, 'DES01', '提拉米苏',      8.00, 0, 1, 1, 70),
(1, 6, 'DES02', '芒果布丁',      5.00, 0, 1, 1, 65),
(1, 7, 'FRU01', '时令水果拼盘',  6.00, 0, 1, 1, 72);

-- 菜品配方 (红烧牛腩)
INSERT INTO dish_recipes (dish_id, ingredient_id, quantity, unit, is_main) VALUES
(1, 2, 0.5, 'kg', 1),       -- 牛腩肉 0.5kg
(1, 18, 0.3, 'kg', 0),      -- 土豆 0.3kg
(1, 19, 0.1, 'kg', 0);      -- 胡萝卜 0.1kg

-- 菜品配方 (蒜蓉粉丝蒸虾)
INSERT INTO dish_recipes (dish_id, ingredient_id, quantity, unit, is_main) VALUES
(6, 13, 0.3, 'kg', 1);      -- 基围虾 0.3kg

-- 近7天销售数据
INSERT INTO sales_records (store_id, business_date, total_customers, total_amount, buffet_amount, single_amount) VALUES
(1, DATEADD('DAY', -6, CURDATE()), 286, 17160.00, 15730.00, 1430.00),
(1, DATEADD('DAY', -5, CURDATE()), 312, 18720.00, 17160.00, 1560.00),
(1, DATEADD('DAY', -4, CURDATE()), 298, 17880.00, 16390.00, 1490.00),
(1, DATEADD('DAY', -3, CURDATE()), 345, 20700.00, 18975.00, 1725.00),
(1, DATEADD('DAY', -2, CURDATE()), 320, 19200.00, 17600.00, 1600.00),
(1, DATEADD('DAY', -1, CURDATE()), 301, 18060.00, 16555.00, 1505.00),
(1, CURDATE(),                  0,      0.00,     0.00,    0.00);

-- 盈利目标
INSERT INTO profit_targets (store_id, target_year, target_month, monthly_target, daily_target, status) VALUES
(1, YEAR(CURDATE()), MONTH(CURDATE()), 150000.00, 5000.00, 0),
(2, YEAR(CURDATE()), MONTH(CURDATE()), 90000.00, 3000.00, 0);

-- 库存记录 (门店1 - 朝阳旗舰店)
INSERT INTO inventory (store_id, ingredient_id, batch_no, quantity, unit_cost, production_date, expiry_date, status) VALUES
(1, 1,  'BATCH20250501', 50.0, 68.00, DATEADD('DAY', -10, CURDATE()), DATEADD('DAY', -1, CURDATE()), 3),
(1, 1,  'BATCH20250515', 30.0, 68.00, DATEADD('DAY', -5, CURDATE()), DATEADD('DAY', 5, CURDATE()), 1),
(1, 2,  'BATCH20250516', 25.0, 58.00, DATEADD('DAY', -4, CURDATE()), DATEADD('DAY', 6, CURDATE()), 1),
(1, 4,  'BATCH20250510', 15.0, 32.00, DATEADD('DAY', -8, CURDATE()), DATEADD('DAY', 2, CURDATE()), 2),
(1, 5,  'BATCH20250518', 40.0, 36.00, DATEADD('DAY', -2, CURDATE()), DATEADD('DAY', 8, CURDATE()), 1),
(1, 7,  'BATCH20250517', 35.0, 22.00, DATEADD('DAY', -3, CURDATE()), DATEADD('DAY', 7, CURDATE()), 1),
(1, 10, 'BATCH20250519', 20.0, 25.00, DATEADD('DAY', -1, CURDATE()), DATEADD('DAY', 3, CURDATE()), 2),
(1, 13, 'BATCH20250519', 12.0, 55.00, DATEADD('DAY', -1, CURDATE()), DATEADD('DAY', 3, CURDATE()), 2),
(1, 14, 'BATCH20250517', 8.0,  65.00, DATEADD('DAY', -3, CURDATE()), DATEADD('DAY', 5, CURDATE()), 1),
(1, 18, 'BATCH20250501', 10.0,  3.00, DATEADD('DAY', -10, CURDATE()), DATEADD('DAY', 10, CURDATE()), 2),
(1, 18, 'BATCH20250518', 60.0,  3.00, DATEADD('DAY', -2, CURDATE()), DATEADD('DAY', 18, CURDATE()), 1),
(1, 20, 'BATCH20250515', 30.0,  4.00, DATEADD('DAY', -5, CURDATE()), DATEADD('DAY', 15, CURDATE()), 1);

-- 食材消耗记录
INSERT INTO ingredient_consumption (store_id, business_date, ingredient_id, consumption_qty, consumption_cost) VALUES
(1, DATEADD('DAY', -1, CURDATE()), 2,  8.0,  464.00),
(1, DATEADD('DAY', -1, CURDATE()), 18, 15.0,  45.00),
(1, DATEADD('DAY', -1, CURDATE()), 13, 6.0,  330.00),
(1, DATEADD('DAY', -1, CURDATE()), 5,  10.0, 360.00);
