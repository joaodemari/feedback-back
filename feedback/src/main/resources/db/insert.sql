INSERT INTO MEMBER (ID, NAME, PHOTO_URL) VALUES 
  (1, 'João Demari', ''),
  (2, 'Guilherme Kuhn', ''),
  (3, 'Leonardo Wingert', '');

INSERT INTO FEEDBACK (ID, FROM_MEMBER_ID, TO_MEMBER_ID, TOPICS, MESSAGE, CREATED_AT) VALUES
    (4, 1, 3, '[2,5]', 'Ótimo trabalho em equipe.', '2024-06-04T14:20:00'),
    (5, 2, 1, '[3]', 'Precisa ser mais pontual.', '2024-06-05T08:45:00'),
    (6, 3, 2, '[1,4]', 'Sempre disposto a ajudar.', '2024-06-06T16:10:00'),
    (7, 1, 2, '[2,3,5]', 'Boa apresentação no projeto.', '2024-06-07T13:00:00'),
    (8, 2, 3, '[1,5]', 'Sugiro mais atenção aos detalhes.', '2024-06-08T10:30:00'),
    (9, 3, 1, '[4]', 'Excelente organização.', '2024-06-09T11:50:00');
    (1, 1, 2, '[1,2,3]', null, '2024-06-01T10:00:00'),
    (2, 2, 3, '[2,4]', 'Precisa melhorar a comunicação.', '2024-06-02T11:30:00'),
    (3, 3, 1, '[1,3,5]', 'Excelente liderança.', '2024-06-03T09:15:00');

