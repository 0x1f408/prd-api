USE `prd`;
INSERT INTO `SIXTH_proposal_comments`(
  `comment_on_proposal`,
  `comment_on_segment`,
  `comment_author`,
  `comment_body`
) VALUES(
 'DEMO',
  NULL,
  'Kit.Peabody',
  'This is a comment on the proposal'
),(
  'DEMO',
  'DEMO01',
  'Kyle.Sullivan',
  'This is a comment on Segment DEMO01'
),(
    'DEMO',
  'DEMO01',
  'Kit.Peabody',
  'Lorem ipsum, si dolor amet.'
),(
    'DEMO',
  'DEMO02',
  'Kit.Peabody',
  'This is also a comment, but on another segment.'
);