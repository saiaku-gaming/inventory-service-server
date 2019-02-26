CREATE TABLE inventory_item (
  inventory_item_id SERIAL PRIMARY KEY,
  character_name TEXT NOT NULL,
  item_name TEXT NOT NULL,
  position_x INTEGER NOT NULL,
  position_y INTEGER NOT NULL
);