SELECT
    resident_id,
    first_name,
    last_name,
    unit,
    address,
    is_active
FROM resident
WHERE resident_id = :resident_id ;
