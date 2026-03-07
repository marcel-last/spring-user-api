-- Test seed data — DO NOT run in production.
--
-- The hashes below are BCrypt-formatted but were generated without a real BCrypt library.
-- For proper hashes, regenerate them using: htpasswd -nbBC 10 "" 'YourPassword' | cut -d: -f2
-- Or use the Spring Boot app's POST /api/v1/users endpoint to create users with real hashes.
--
-- Plaintext passwords for reference:
--   alice.nakamura@example.com               gNcMYdqjh0UrPZ%Q
--   ben.torres@example.com                   M64*LA5ZO8XQR&Md
--   clara.johansson@example.com              4SGM2lA?6@qQZlVv
--   david.okafor@example.com                 8h%V0kDEI0?zjlO8
--   elena.petrov@example.com                 #F9j@6S8skmDQuJ%
--   felix.moreau@example.com                 1?&Q%cSUi*$l05KL
--   grace.kim@example.com                    cSLaXJ3txrYeeH$9
--   hassan.elamin@example.com                Crtx#rF818uFnDV!
--   ingrid.larsen@example.com                %UpZt#e6ZW2nM2Ia
--   james.whitfield@example.com              U@6UfOswVhNcsvN$
--   keiko.tanaka@example.com                 u9QzQlXTa6?i925&
--   luca.bianchi@example.com                 f!psMaDZt4aSDQ1g
--   marta.kowalski@example.com               k$9R9V*h12&ZbC48
--   nikolai.volkov@example.com               i*SB$$8nwLjLbt3&
--   olivia.santos@example.com                IU$i#!b6R%4WEKZn
--   priya.sharma@example.com                 0ETOEMlGHuf%@3ro
--   quinn.murphy@example.com                 W9!pY2BA89kn$WO4
--   rafael.gutierrez@example.com             rFK1LtRuxtwfS?7s
--   sofia.weber@example.com                  60Dn0y71%GMA7!Qa
--   tariq.hassan@example.com                 f0*9mwFUs8TErtPx

INSERT INTO users (first_name, last_name, email, password_hash, role, enabled)
VALUES
    ('Alice',    'Nakamura',  'alice.nakamura@example.com',   '$2a$10$QAbe8NOEByyJXAxNAdAHdgKj+bttji0N/Qs/ZYmLOG/veGxS++7Em', 'ADMIN', TRUE),
    ('Ben',      'Torres',    'ben.torres@example.com',       '$2a$10$JWJfXlGaELFi5SSScfDzCA3SIRS3bmxNFM7E0TQHvRWX8b7htZH0x', 'USER',  TRUE),
    ('Clara',    'Johansson', 'clara.johansson@example.com',  '$2a$10$aViaQ3ezZP/zSxkr198UrAMMVAOcwjTkKrgBS5fonn76Y9K5Oo7we', 'USER',  TRUE),
    ('David',    'Okafor',    'david.okafor@example.com',     '$2a$10$B1j03MARUvizejmVyAVSDA9JjzidOYcPp9UlfLB/hsQFJX+vq7Rzt', 'USER',  TRUE),
    ('Elena',    'Petrov',    'elena.petrov@example.com',     '$2a$10$OIAf1aQTfiFPSu4rSl4wRwo/9ZfuvhEQfEOJSpxMUeQf4S31pz2qX', 'USER',  FALSE),
    ('Felix',    'Moreau',    'felix.moreau@example.com',     '$2a$10$vx5J6tvv5zR2rXTNjoCHcQ7Js2womByyWhdkus7Nl7z9x5/4Yvgkw', 'USER',  TRUE),
    ('Grace',    'Kim',       'grace.kim@example.com',        '$2a$10$kJXB6asaY8T1RysBox/S0QRPkaC1gxXI2OcCgtphCYkT5JPSMMWvu', 'USER',  TRUE),
    ('Hassan',   'El-Amin',   'hassan.elamin@example.com',    '$2a$10$JxxCl5nxX7Mq5xy3NzdWYgVlOu+aDYGrvV9sTWm2n7pYQ7QmcFw0j', 'USER',  TRUE),
    ('Ingrid',   'Larsen',    'ingrid.larsen@example.com',    '$2a$10$/2xnpCVeS2DiDdLJ4GxctQaYj1cP8SvGCZ3XRQbHh3pqzVe5d9hbI', 'ADMIN', TRUE),
    ('James',    'Whitfield', 'james.whitfield@example.com',  '$2a$10$6M691LOo8WxRlxdiFSluYw4mxmzOXhPEdV9OSpehRB0BJuK0R3Sql', 'USER',  TRUE),
    ('Keiko',    'Tanaka',    'keiko.tanaka@example.com',     '$2a$10$Qz5I9/wKXG9PW+Zk71mgTwPCY0Usk8Jwo+hgCubWmKE7H+HE+LIbB', 'USER',  FALSE),
    ('Luca',     'Bianchi',   'luca.bianchi@example.com',     '$2a$10$gA+tFbjvDvL31pIFCQq8QwfYgP90MZkYkieVM+LddUIN98Xh2TmZl', 'USER',  TRUE),
    ('Marta',    'Kowalski',  'marta.kowalski@example.com',   '$2a$10$oihIBYlKGpyUEbLF8Mm58gdn+UUoV2YM/bpzcL+oBdWl2krM3MfOq', 'USER',  TRUE),
    ('Nikolai',  'Volkov',    'nikolai.volkov@example.com',   '$2a$10$7ZnuwUM4OMVpL+lPew895AwN3cxGefNGYoSMt86s9sMcx61QuxCk+', 'USER',  TRUE),
    ('Olivia',   'Santos',    'olivia.santos@example.com',    '$2a$10$oJZPjqvai3FlFRqOyHjOTwzqOwnVgjhYgp365zss6NmvEUraLMq2D', 'USER',  FALSE),
    ('Priya',    'Sharma',    'priya.sharma@example.com',     '$2a$10$04t9x4ZbXjd6tuLmOX8cgAKkn9e9X3tyiKsJMdln9Jj8/iX9eHivX', 'USER',  TRUE),
    ('Quinn',    'Murphy',    'quinn.murphy@example.com',     '$2a$10$F8EsKc7m4STdJQbEPl5zZg1oj0hPLwWfzgGgm8hXdFlI/J9lyEjA+', 'USER',  TRUE),
    ('Rafael',   'Gutierrez', 'rafael.gutierrez@example.com', '$2a$10$OTiBRQkuYfvGzuQhpzs08woH9sPAmdm1QMlgC5jPOGBPC7A+5xNgl', 'USER',  TRUE),
    ('Sofia',    'Weber',     'sofia.weber@example.com',      '$2a$10$+ZpNoCqUDwuCBF+hSNJZQgf0wr3sgpw54PbnG8aLnyvtcUgDbALJz', 'USER',  TRUE),
    ('Tariq',    'Hassan',    'tariq.hassan@example.com',     '$2a$10$nqlPMtuHAI7A85LJD8HxQQGDw3OAXQr5J3xtLOac8addIHEM3v8te', 'USER',  TRUE);