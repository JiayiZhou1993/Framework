package cs4620.mesh.gen;

import cs4620.mesh.MeshData;
import egl.NativeMem;
import egl.math.Matrix4;
import egl.math.Vector3;

/**
 * Generates A Torus Mesh
 * @author Cristian
 *
 */
public class MeshGenTorus extends MeshGenerator {
	@Override
	public void generate(MeshData outData, MeshGenOptions opt) {
		// TODO#A1 SOLUTION START
		int n=opt.divisionsLongitude;
		int m=opt.divisionsLatitude;
		float r=opt.innerRadius;
		
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
				outData.positions.put((float) Math.sin(2*Math.PI*j/n-Math.PI) + r * (float) (Math.cos(2*Math.PI*i/m)*Math.sin(2*Math.PI*j/n)));
				outData.positions.put(r* (float) Math.sin(2*Math.PI*i/m));
				outData.positions.put((float) Math.cos(2*Math.PI*j/n-Math.PI) + r * (float) (Math.cos(2*Math.PI*i/m)*Math.cos(2*Math.PI*j/n)));
			}
		}
		
		// Add Normals
		for(int i=0;i < m+1;i++) {
			for(int j=0;j<n+1;j++){
				outData.normals.put((float) r * (float) (Math.cos(2*Math.PI*i/m)*Math.sin(2*Math.PI*j/n)));
				outData.normals.put(r* (float) Math.sin(2*Math.PI*i/m));
				outData.normals.put((float) r * (float) (Math.cos(2*Math.PI*i/m)*Math.cos(2*Math.PI*j/n)));
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
