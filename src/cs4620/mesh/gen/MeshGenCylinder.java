package cs4620.mesh.gen;

import cs4620.mesh.MeshData;
import egl.NativeMem;

/**
 * Generates A Cylinder Mesh
 * @author Cristian (Original)
 * @author Jimmy (Revised 8/25/2015)
 */
public class MeshGenCylinder extends MeshGenerator {
	@Override
	public void generate(MeshData outData, MeshGenOptions opt) {
		// TODO#A1 SOLUTION START
		// Calculate Vertex And Index Count
		int n=opt.divisionsLongitude;
		outData.vertexCount = 2 + 2 * (n + 1) + 2 * n;
		outData.indexCount = n * 2 * 2 * 3;

		// Create Storage Spaces
		outData.positions = NativeMem.createFloatBuffer(outData.vertexCount * 3);
		outData.uvs = NativeMem.createFloatBuffer(outData.vertexCount * 2);
		outData.normals = NativeMem.createFloatBuffer(outData.vertexCount * 3);
		outData.indices = NativeMem.createIntBuffer(outData.indexCount);
		
		// Add Positions For outer surface
		for(int i=0;i < n+1;i++) {
			outData.positions.put((float) Math.sin(i*2*Math.PI/n));
			outData.positions.put(1);
			outData.positions.put((float) Math.cos(i*2*Math.PI/n));
		}
		
		for(int i=0;i < n+1;i++) {
			outData.positions.put((float) Math.sin(i*2*Math.PI/n));
			outData.positions.put(-1);
			outData.positions.put((float) Math.cos(i*2*Math.PI/n));
		}
		
		// Add Positions For two caps
		outData.positions.put(0);
		outData.positions.put(-1);
		outData.positions.put(0);
		
		for(int i=0;i < n;i++) {
			outData.positions.put((float) Math.sin(i*2*Math.PI/n));
			outData.positions.put(-1);
			outData.positions.put((float) Math.cos(i*2*Math.PI/n));
		}
		
		outData.positions.put(0);
		outData.positions.put(1);
		outData.positions.put(0);
		
		for(int i=0;i < n;i++) {
			outData.positions.put((float) Math.sin(i*2*Math.PI/n));
			outData.positions.put(1);
			outData.positions.put((float) Math.cos(i*2*Math.PI/n));
		}
	
		// Add Normals
		
		for(int i=0;i<n+1;i++){
			outData.normals.put((float) Math.sin(i*2*Math.PI/n));
			outData.normals.put(0);
			outData.normals.put((float) Math.cos(i*2*Math.PI/n));
		}
		
		for(int i=0;i<n+1;i++){
			outData.normals.put((float) Math.sin(i*2*Math.PI/n));
			outData.normals.put(0);
			outData.normals.put((float) Math.cos(i*2*Math.PI/n));
		}
		
		for(int i=0;i<n+1;i++){
			outData.normals.put(0);
			outData.normals.put(-1);
			outData.normals.put(0);
		}
		
		for(int i=0;i<n+1;i++){
			outData.normals.put(0);
			outData.normals.put(1);
			outData.normals.put(0);
		}
		// Add UV Coordinates
		for(int i = 0; i < n+1; i++){
			outData.uvs.put((float) i/n);
			outData.uvs.put((float) 0.5);
		}
		
		for(int i = 0; i < n+1; i++){
			outData.uvs.put((float) i/n);
			outData.uvs.put(0);
		}

		outData.uvs.put((float) 0.25);
		outData.uvs.put((float) 0.75);
		
		for(int i = 0; i < n; i++){
			outData.uvs.put((float) (0.25+0.25*Math.sin(i*2*Math.PI/n)));
			outData.uvs.put((float) (0.75+0.25*Math.cos(i*2*Math.PI/n)));
		}
		
		outData.uvs.put((float) 0.75);
		outData.uvs.put((float) 0.75);
		
		for(int i = 0; i < n; i++){
			outData.uvs.put((float) (0.75+0.25*Math.sin(i*2*Math.PI/n)));
			outData.uvs.put((float) (0.75-0.25*Math.cos(i*2*Math.PI/n)));
		}
		
		// Add Indices
		for(int i = 0;i < n;i++) {
			outData.indices.put(i);
			outData.indices.put(n+1+i);
			outData.indices.put(i+1);
			outData.indices.put(i+1);
			outData.indices.put(n+1+i);
			outData.indices.put(n+1+i+1);
		}
		
		for(int i = 0;i < n;i++) {
			if(i!=n-1){
				outData.indices.put(2*n+2);
				outData.indices.put(2*n+2+i+2);
				outData.indices.put(2*n+2+i+1);
			}
			else{
				outData.indices.put(2*n+2);
				outData.indices.put(2*n+2+1);
				outData.indices.put(2*n+2+i+1);
			}
		}
		
		for(int i = 0;i < n;i++) {
			if(i!=n-1){
				outData.indices.put(3*n+3);
				outData.indices.put(3*n+3+i+1);
				outData.indices.put(3*n+3+i+2);
			}
			else{
				outData.indices.put(3*n+3);
				outData.indices.put(3*n+3+i+1);
				outData.indices.put(3*n+3+1);
			}
		}
		// #SOLUTION END
	}
}
