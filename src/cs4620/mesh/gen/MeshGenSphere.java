package cs4620.mesh.gen;

import cs4620.mesh.MeshData;
import egl.NativeMem;

/**
 * Generates A Sphere Mesh
 * @author Cristian
 *
 */
public class MeshGenSphere extends MeshGenerator {
	@Override
	public void generate(MeshData outData, MeshGenOptions opt) {
		// TODO#A1 SOLUTION START

		// Calculate Vertex And Index Count
		int n=opt.divisionsLongitude;
		int m=opt.divisionsLatitude;
		outData.vertexCount = (n+1)*(m+1);
		outData.indexCount = n * m * 3 * 2; 

		// Create Storage Spaces
		outData.positions = NativeMem.createFloatBuffer(outData.vertexCount * 3);
		outData.uvs = NativeMem.createFloatBuffer(outData.vertexCount * 2);
		outData.normals = NativeMem.createFloatBuffer(outData.vertexCount * 3);
		outData.indices = NativeMem.createIntBuffer(outData.indexCount);
		
		
		// Create The Vertices
		for(int i=0;i < m+1;i++) {
			for(int j=0;j<n+1;j++){
				outData.positions.put((float) (Math.cos(Math.PI/2-i*Math.PI/m) * Math.sin(j*2*Math.PI/n-Math.PI)));
				outData.positions.put((float) Math.sin(Math.PI/2-i*Math.PI/m));
				outData.positions.put((float) (Math.cos(Math.PI/2-i*Math.PI/m) * Math.cos(j*2*Math.PI/n-Math.PI)));
			}
		}
		
		// Add Normals
		for(int i=0;i < m+1;i++) {
			for(int j=0;j<n+1;j++){
				outData.normals.put((float) (Math.cos(Math.PI/2-i*Math.PI/m) * Math.sin(j*2*Math.PI/n-Math.PI)));
				outData.normals.put((float) Math.sin(Math.PI/2-i*Math.PI/m));
				outData.normals.put((float) (Math.cos(Math.PI/2-i*Math.PI/m) * Math.cos(j*2*Math.PI/n-Math.PI)));
			}
		}
		
		// Add UV coordinates
		for(int i=0;i < m+1;i++) {
			for(int j=0;j<n+1;j++){
				outData.uvs.put((float) j/n);
				outData.uvs.put(1- (float)i/m);
			}
		}
		// Create The Indices
		for(int i = 0;i < m;i++) {
			for(int j=0;j<n;j++){
				outData.indices.put((n+1)*i+j);
				outData.indices.put(n+1+(n+1)*i+j);
				outData.indices.put(n+1+(n+1)*i+j+1);
				outData.indices.put((n+1)*i+j);
				outData.indices.put(n+1+(n+1)*i+j+1);
				outData.indices.put((n+1)*i+j+1);
			}
		}		
		// #SOLUTION END
	}
}
