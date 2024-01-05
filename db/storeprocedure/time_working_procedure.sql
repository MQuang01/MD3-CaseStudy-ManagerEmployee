CREATE DEFINER=`root`@`localhost` PROCEDURE `time_working_procedure`(IN input_month int, IN input_year int)
BEGIN

    DECLARE start_date DATE;
    DECLARE end_date DATE;
    
    -- Tính ngày bắt đầu và kết thúc của tháng
    SET start_date = STR_TO_DATE(CONCAT(input_year, '-', input_month, '-01'), '%Y-%m-%d');
    SET end_date = LAST_DAY(start_date);
    
    -- In ra danh sách ID và tổng số giờ làm việc trong tháng
    
        SELECT `timekeepings`.`members_id` as id,
			members.`name` ,
           SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(time_checkout, time_checkin)))) AS total_time
    FROM timekeepings 
    join members on members.id=`timekeepings`.`members_id`
   WHERE DATE_FORMAT(day, '%Y-%m') = CONCAT(input_year, '-', LPAD(input_month, 2, '0'))
    GROUP BY members_id,members.`name`;
END