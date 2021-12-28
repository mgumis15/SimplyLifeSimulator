package agh.ics.oop;

public enum MapDirection {
    NORTH,
    NORTH_WEST,
    WEST,
    SOUTH_WEST,
    SOUTH,
    SOUTH_EAST,
    EAST,
    NORTH_EAST;

    public String toString(){
        switch(this) {
            case NORTH: return "Północ";
            case NORTH_WEST: return "Północ zachód";
            case WEST: return "Zachód";
            case SOUTH_WEST: return "Południowy zachód";
            case SOUTH: return "Południe";
            case SOUTH_EAST: return "Południowy wschód";
            case EAST: return "Wschód";
            case NORTH_EAST: return "Północny wschód";
            default: return "";
        }
    }

    public MapDirection next(){

            switch (this) {
                case NORTH:
                    return NORTH_EAST;
                case NORTH_EAST:
                    return EAST;
                case EAST:
                    return SOUTH_EAST;
                case SOUTH_EAST:
                    return SOUTH;
                case SOUTH:
                    return SOUTH_WEST;
                case SOUTH_WEST:
                    return WEST;
                case WEST:
                    return NORTH_WEST;
                default:
                    return NORTH;
            }
    }


    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH: return new Vector2d(0,1);
            case NORTH_EAST: return new Vector2d(1,1);
            case NORTH_WEST: return new Vector2d(-1,1);
            case SOUTH: return new Vector2d(0,-1);
            case SOUTH_EAST: return new Vector2d(1,-1);
            case SOUTH_WEST: return new Vector2d(-1,-1);
            case WEST: return new Vector2d(-1,0);
            default: return new Vector2d(1,0);
        }
    }
}
