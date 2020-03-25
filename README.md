# TDD「Locker Robot」
Given 19个空柜子 When 用户开1个柜子存包 Then 成功出票1个，存包成功，空柜子-1
Given 0个空柜子 When 用户开1个柜子存包 Then 出票失败，存包失败，空柜子0

Given 19个空柜子 When 用户取包 Then 检票失败，系统提醒‘没有存储包裹’  空柜子不变
Given 一张使用过的票 When 用户取包 Then 检票失败，系统提醒‘取包失败’ 空柜子不变
Given 一张假票 When 用户取包 Then 检票失败，系统提醒‘验票失败’ 空柜子不变
Given 一张1号柜子正确的票 When 用户取包 Then 检票成功，打开一号柜子 空柜子+1
