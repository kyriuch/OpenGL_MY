
attribute vec3 position;

varying vec3 colour;

void main() {
    gl_Position = vec4(position, 0);
    colour = vec3(1.0, 1.0, 1.0);
}